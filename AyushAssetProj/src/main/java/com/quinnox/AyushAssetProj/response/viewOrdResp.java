package com.quinnox.AyushAssetProj.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data

public class viewOrdResp {

	
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<order> orderlist = new ArrayList<>();
	@Override
	public String toString() {
		return "viewOrdResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN + ", orderlist="
				+ orderlist + "]";
	}
	
	
	}

