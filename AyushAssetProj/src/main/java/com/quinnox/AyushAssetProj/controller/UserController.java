package com.quinnox.AyushAssetProj.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.quinnox.AyushAssetProj.dao.CartDao;
import com.quinnox.AyushAssetProj.entities.Address;
import com.quinnox.AyushAssetProj.entities.Bufcart;
import com.quinnox.AyushAssetProj.entities.PlaceOrder;
import com.quinnox.AyushAssetProj.entities.Product;
import com.quinnox.AyushAssetProj.entities.User;
import com.quinnox.AyushAssetProj.response.cartResp;
import com.quinnox.AyushAssetProj.response.prodResp;
import com.quinnox.AyushAssetProj.response.response;
import com.quinnox.AyushAssetProj.response.serverResp;
import com.quinnox.AyushAssetProj.response.userResp;
import com.quinnox.AyushAssetProj.service.AddressService;
import com.quinnox.AyushAssetProj.service.CartService;
import com.quinnox.AyushAssetProj.service.OrderService;
import com.quinnox.AyushAssetProj.service.ProductService;
import com.quinnox.AyushAssetProj.service.UserService;
import com.quinnox.AyushAssetProj.util.Validator;
import com.quinnox.AyushAssetProj.util.jwtUtil;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {


	
	
	@Autowired
	private UserService userServ;

	@Autowired
	private AddressService addrServ;

	@Autowired
	private ProductService prodServ;

	
	@Autowired
	private CartService cartServ;
	
	
	@Autowired
	private CartDao cartDao;

	@Autowired
	private OrderService ordServ;

	@Autowired
	private jwtUtil jwtutil;
	
	

	
	@PostMapping("/getProducts")
	public ResponseEntity<prodResp> getProducts(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN)
			throws IOException {

		prodResp resp = new prodResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				resp.setStatus("200");
				resp.setMessage("LIST_PRO");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setOblist(prodServ.findAll());
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/addToCart")
	public ResponseEntity<serverResp> addToCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam("productId") String productId) throws IOException {

		serverResp resp = new serverResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
				Product cartItem = prodServ.findByProductid(Integer.parseInt(productId));

				Bufcart buf = new Bufcart();
				buf.setEmail(loggedUser.getEmail());
				buf.setQuantity(1);
				buf.setPrice(cartItem.getPrice());
				buf.setProductId(Integer.parseInt(productId));
				buf.setProductname(cartItem.getProductname());
				Date date = new Date();
				buf.setDateAdded(date);

				cartServ.save(buf);
				resp.setStatus("200");
				resp.setMessage("CART_UPD");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
	}
	@GetMapping("/viewCart")
	public ResponseEntity<cartResp> viewCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN)
			throws IOException {

		cartResp resp = new cartResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {				
				User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
				resp.setStatus("200");
				resp.setMessage("CART_UPD");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setOblist(cartServ.findByEmail(loggedUser.getEmail()));
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/updateCart")
	public ResponseEntity<cartResp> updateCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam(name = "bufcartid") String bufcartid,
			@RequestParam(name = "quantity") String quantity) throws IOException {

		cartResp resp = new cartResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
				Bufcart selCart = cartServ.findByBufcartIdAndEmail(Integer.parseInt(bufcartid), loggedUser.getEmail());
				selCart.setQuantity(Integer.parseInt(quantity));
				cartServ.save(selCart);
				List<Bufcart> bufcartlist = cartServ.findByEmail(loggedUser.getEmail());
				resp.setStatus("200");
				resp.setMessage("UPD_CART");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setOblist(bufcartlist);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/delCart")
	public ResponseEntity<cartResp> delCart(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
			@RequestParam(name = "bufcartid") String bufcartid) throws IOException {

		cartResp resp = new cartResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
				cartDao.deleteByBufcartIdAndEmail(Integer.parseInt(bufcartid), loggedUser.getEmail());
				List<Bufcart> bufcartlist = cartDao.findByEmail(loggedUser.getEmail());
				resp.setStatus("200");
				resp.setMessage("DEL_CART");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setOblist(bufcartlist);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<cartResp>(resp, HttpStatus.ACCEPTED);
	}


	@GetMapping("/placeOrder")
	public ResponseEntity<serverResp> placeOrder(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN)
			throws IOException {

		serverResp resp = new serverResp();
		if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User loggedUser = jwtutil.checkToken(AUTH_TOKEN);
				PlaceOrder po = new PlaceOrder();
				po.setEmail(loggedUser.getEmail());
				Date date = new Date();
				po.setOrderDate(date);
				po.setOrderStatus("Processing");
				double total = 0;
				List<Bufcart> buflist = cartServ.findAllByEmail(loggedUser.getEmail());
				for (Bufcart buf : buflist) {
					total = +(buf.getQuantity() * buf.getPrice());
				}
				po.setTotalCost(total);
				PlaceOrder res = ordServ.save(po);
				buflist.forEach(bufcart -> {
					bufcart.setOrderId(res.getOrderId());
					cartServ.save(bufcart);

				});
				resp.setStatus("200");
				resp.setMessage("PLA_ORD");
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/addAddress")
	public ResponseEntity<userResp> addAddress( @RequestBody Address address,
			@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {
		userResp resp = new userResp();
		if (Validator.isAddressEmpty(address)) {
			resp.setStatus("400");
			resp.setMessage("BAD_REQUEST");
		} else if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User user = jwtutil.checkToken(AUTH_TOKEN);
				user.setAddress(address);
				address.setUser(user);
				Address adr = addrServ.saveAndFlush(address);
				resp.setStatus("200");
				resp.setMessage("ADR_UPD");
				resp.setUser(user);
				resp.setAddress(adr);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<userResp>(resp, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<serverResp> addUser(@RequestBody User user) {

		serverResp resp = new serverResp();
		try {
			if (Validator.isUserEmpty(user)) {
				resp.setStatus("400");
				resp.setMessage("BAD_REQUEST");
			} else if (!Validator.isValidEmail(user.getEmail())) {
				resp.setStatus("400");
				resp.setMessage("INVALID_EMAIL");
			} else {
				resp.setStatus("200");
				resp.setMessage("REGISTERED");
				User reg = userServ.save(user);
				resp.setObject(reg);
			}
		} catch (Exception e) {
			resp.setStatus("500");
			resp.setMessage(e.getMessage());
		}
		return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
	}

	@PostMapping("/verify")
	public ResponseEntity<serverResp> verifyUser( @RequestBody HashMap<String, String> credential) {

		String email=credential.get("email");
		String password=credential.get("password");
		User loggedUser = userServ.findByEmailAndPasswordAndUsertype(email, password, "customer");
		serverResp resp = new serverResp();
		if (loggedUser != null) {
			String jwtToken = jwtutil.createToken(email, password, "customer");
			resp.setStatus("200");
			resp.setMessage("SUCCESS");
			resp.setAUTH_TOKEN(jwtToken);
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<serverResp>(resp, HttpStatus.OK);
	}
	
	@PostMapping("/getAddress")
	public ResponseEntity<response> getAddress(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN) {

		response resp = new response();
		if (jwtutil.checkToken(AUTH_TOKEN) != null) {
			try {
				User user = jwtutil.checkToken(AUTH_TOKEN);
				Address adr = addrServ.findByUser(user);

				HashMap<String, String> map = new HashMap<>();
				map.put("address", adr.getAddress());
				map.put("city", adr.getCity());
				map.put("state", adr.getState());
				map.put("country", adr.getCountry());
				map.put("zipcode", String.valueOf(adr.getZipcode()));
				map.put("phonenumber", adr.getPhonenumber());

				resp.setStatus("200");
				resp.setMessage("ADR_UPD");
				resp.setMap(map);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus("500");
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus("500");
			resp.setMessage("ERROR");
		}
		return new ResponseEntity<response>(resp, HttpStatus.ACCEPTED);
	}
	

	
	
}
