package com.quinnox.AyushAssetProj.service;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.Bufcart;

public interface CartService {
	
	
	public	List<Bufcart> findByEmail(String email);

	public	Bufcart findByBufcartIdAndEmail(int bufcartId, String email);

	public	void deleteByBufcartIdAndEmail(int bufcartId, String email);

	public	List<Bufcart> findAllByEmail(String email);

	public	List<Bufcart> findAllByOrderId(int orderId);

	public Bufcart save(Bufcart bufcart);

	

}
