package com.quinnox.AyushAssetProj.response;

import java.util.HashMap;

import lombok.Data;
@Data
public class response {

	
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private HashMap<String, String> map;
}
