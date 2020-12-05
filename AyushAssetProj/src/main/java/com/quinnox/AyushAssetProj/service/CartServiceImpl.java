package com.quinnox.AyushAssetProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.AyushAssetProj.dao.CartDao;
import com.quinnox.AyushAssetProj.entities.Bufcart;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
	@Override
	public List<Bufcart> findByEmail(String email) {
		
		return cartDao.findByEmail(email);
	}

	@Override
	public Bufcart findByBufcartIdAndEmail(int bufcartId, String email) {
		
		return cartDao.findByBufcartIdAndEmail(bufcartId, email);
	}

	@Override
	public void deleteByBufcartIdAndEmail(int bufcartId, String email) {
		
		
	}

	@Override
	public List<Bufcart> findAllByEmail(String email) {
		
		return cartDao.findAllByEmail(email);
	}

	@Override
	public List<Bufcart> findAllByOrderId(int orderId) {
		
		return cartDao.findAllByOrderId(orderId);
	}

	@Override
	public Bufcart save(Bufcart bufcart) {
	
		return cartDao.save(bufcart);
	}



	

}
