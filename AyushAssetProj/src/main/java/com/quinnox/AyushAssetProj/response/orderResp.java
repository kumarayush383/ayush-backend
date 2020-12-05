package com.quinnox.AyushAssetProj.response;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.Bufcart;

import lombok.Data;

@Data

public class orderResp {

	
	private int orderId;
	private List<Bufcart> cartList;
	
	
	@Override
	public String toString() {
		return "orderResp [orderId=" + orderId + ", cartList=" + cartList + "]";
	}
}
