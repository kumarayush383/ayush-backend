package com.quinnox.AyushAssetProj.response;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.Product;

import lombok.Data;


@Data
public class prodResp {

	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Product> oblist;
}
