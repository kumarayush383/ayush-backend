package com.quinnox.AyushAssetProj.response;

import com.quinnox.AyushAssetProj.entities.Address;
import com.quinnox.AyushAssetProj.entities.User;

import lombok.Data;
@Data
public class userResp {


	private String status;
	private String message;
	private String AUTH_TOKEN;
	private User user;
	private Address address;
}
