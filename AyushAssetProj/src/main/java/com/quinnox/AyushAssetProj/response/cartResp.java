package com.quinnox.AyushAssetProj.response;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.Bufcart;

import lombok.Data;

@Data

public class cartResp {

	
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Bufcart> oblist;
}
