package com.quinnox.AyushAssetProj.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import com.quinnox.AyushAssetProj.dao.ProductDao;
import com.quinnox.AyushAssetProj.entities.PlaceOrder;
import com.quinnox.AyushAssetProj.entities.Product;
import com.quinnox.AyushAssetProj.entities.User;
import com.quinnox.AyushAssetProj.response.order;
import com.quinnox.AyushAssetProj.response.prodResp;
import com.quinnox.AyushAssetProj.response.serverResp;
import com.quinnox.AyushAssetProj.response.viewOrdResp;
import com.quinnox.AyushAssetProj.service.CartService;
import com.quinnox.AyushAssetProj.service.OrderService;
import com.quinnox.AyushAssetProj.service.ProductService;
import com.quinnox.AyushAssetProj.service.UserService;
import com.quinnox.AyushAssetProj.util.Validator;
import com.quinnox.AyushAssetProj.util.jwtUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

		@Autowired
		private UserService userServ;

		@Autowired
		private ProductService prodServ;

		
		@Autowired
		private ProductDao prodDao;
		
		@Autowired
		private OrderService ordServ;

		@Autowired
		private CartService cartServ;

		@Autowired
		private jwtUtil jwtutil;
		
		
		
		
		@PostMapping("/addProduct")
		public ResponseEntity<prodResp> addProduct(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
				@RequestParam(name = "file") MultipartFile prodImage,
				@RequestParam(name = "description") String description,
				@RequestParam(name = "price") String price,
				@RequestParam(name = "productname") String productname,
				@RequestParam(name = "quantity") String quantity) throws IOException {
			prodResp resp = new prodResp();
			if (Validator.isStringEmpty(productname) || Validator.isStringEmpty(description)
					|| Validator.isStringEmpty(price) || Validator.isStringEmpty(quantity)) {
				                 resp.setStatus("400");
				                  resp.setMessage("BAD_REQUEST");
			} else if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
				try {
					Product prod = new Product();
					prod.setDescription(description);
					prod.setPrice(Double.parseDouble(price));
					prod.setProductname(productname);
					prod.setQuantity(Integer.parseInt(quantity));
					prod.setProductimage(prodImage.getBytes());
					prodServ.save(prod);

					resp.setStatus("200");
					resp.setMessage("ADD_PRO");
					resp.setAUTH_TOKEN(AUTH_TOKEN);
					resp.setOblist(prodServ.findAll());
				} catch (Exception e) {
					resp.setStatus("500");
					resp.setMessage(e.getMessage());
					resp.setAUTH_TOKEN(AUTH_TOKEN);
				}
			} else {
				resp.setStatus("400");
				resp.setMessage("BAD_REQUEST");
			}
			return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
		}
		
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
		

		@GetMapping("/delProduct")
		public ResponseEntity<prodResp> delProduct(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
				@RequestParam(name = "productid") String productid) throws IOException {
			prodResp resp = new prodResp();
			if (Validator.isStringEmpty(productid)) {
				resp.setStatus("400");
				resp.setMessage("BAD_REQUEST");
			} else if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
				try {
					prodDao.deleteByProductid(Integer.parseInt(productid));
					resp.setStatus("200");
					resp.setMessage("DEL_PRO");
					resp.setAUTH_TOKEN(AUTH_TOKEN);
					resp.setOblist(prodDao.findAll());
				} catch (Exception e) {
					resp.setStatus("500");
					resp.setMessage(e.toString());
					resp.setAUTH_TOKEN(AUTH_TOKEN);
				}
			} else {
				resp.setStatus("500");
				resp.setMessage("ERROR");
			}
			return new ResponseEntity<prodResp>(resp, HttpStatus.ACCEPTED);
		}
		
		@GetMapping("/viewOrders")
		public ResponseEntity<viewOrdResp> viewOrders(@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN)
				throws IOException {

			viewOrdResp resp = new viewOrdResp();
			if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
				try {
					resp.setStatus("200");
					resp.setMessage("VW_ORD");
					resp.setAUTH_TOKEN(AUTH_TOKEN);
					List<order> orderList = new ArrayList<>();
					List<PlaceOrder> poList = ordServ.findAll();
					poList.forEach((po) -> {
						order ord = new order();
						ord.setOrderBy(po.getEmail());
						ord.setOrderId(po.getOrderId());
						ord.setOrderStatus(po.getOrderStatus());
						ord.setProducts(cartServ.findAllByOrderId(po.getOrderId()));
						orderList.add(ord);
					});
					
					
					resp.setOrderlist(orderList);
				} catch (Exception e) {
					resp.setStatus("500");
					resp.setMessage(e.getMessage());
					resp.setAUTH_TOKEN(AUTH_TOKEN);
				}
			} else {
				resp.setStatus("500");
				resp.setMessage("ERROR");
			}
			return new ResponseEntity<viewOrdResp>(resp, HttpStatus.ACCEPTED);
		}

		@PostMapping("/updateOrder")
		public ResponseEntity<serverResp> updateOrders(
				@RequestHeader(name = "AUTH_TOKEN") String AUTH_TOKEN,
				@RequestParam(name = "orderId") String orderId,
				@RequestParam(name = "orderStatus") String orderStatus) throws IOException {

			serverResp resp = new serverResp();
			if (Validator.isStringEmpty(orderId) || Validator.isStringEmpty(orderStatus)) {
				resp.setStatus("400");
				resp.setMessage("BAD_REQUEST");
			} else if (!Validator.isStringEmpty(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN) != null) {
				try {
					PlaceOrder pc = ordServ.findByOrderId(Integer.parseInt(orderId));
					pc.setOrderStatus(orderStatus);
					ordServ.save(pc);
					resp.setStatus("200");
					resp.setMessage("UPD_ORD");
					resp.setAUTH_TOKEN(AUTH_TOKEN);
				} catch (Exception e) {
					resp.setStatus("500");
					resp.setMessage(e.toString());
					resp.setAUTH_TOKEN(AUTH_TOKEN);
				}
			} else {
				resp.setStatus("500");
				resp.setMessage("ERROR");
			}
			return new ResponseEntity<serverResp>(resp, HttpStatus.ACCEPTED);
		}
		
		@PostMapping("/verify")
		public ResponseEntity<serverResp> verifyUser(@RequestBody HashMap<String, String> credential) {
			String email=credential.get("email");
			String password=credential.get("password");
			User loggedUser = userServ.findByEmailAndPasswordAndUsertype(email, password, "admin");
			serverResp resp = new serverResp();
			if (loggedUser != null) {
				String jwtToken = jwtutil.createToken(email, password,"admin");
				resp.setStatus("200");
				resp.setMessage("SUCCESS");
				resp.setAUTH_TOKEN(jwtToken);
			} else {
				resp.setStatus("500");
				resp.setMessage("ERROR");
			}
			return new ResponseEntity<serverResp>(resp, HttpStatus.OK);
		}
		
	
		
		
		

		
		
		
		
	
	
	
}
