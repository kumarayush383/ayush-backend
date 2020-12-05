package com.quinnox.AyushAssetProj.service;

import com.quinnox.AyushAssetProj.entities.Address;
import com.quinnox.AyushAssetProj.entities.User;

public interface AddressService {

	public Address findByUser(User user);

	

	public Address saveAndFlush(Address address);

	
}
