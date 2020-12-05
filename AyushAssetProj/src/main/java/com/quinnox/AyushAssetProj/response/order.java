package com.quinnox.AyushAssetProj.response;

import java.util.ArrayList;
import java.util.List;

import com.quinnox.AyushAssetProj.entities.Bufcart;

import lombok.Data;


@Data
public class order {

	
	private int orderId;
	private String orderBy;
	private String orderStatus;
	private List<Bufcart> products = new ArrayList<>();
		
	
}
