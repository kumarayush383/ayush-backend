package com.quinnox.AyushAssetProj.response;



import lombok.Data;

@Data
public class serverResp {

	
	private String status;
	private String message;
	private String AUTH_TOKEN;

	private Object object;
}
