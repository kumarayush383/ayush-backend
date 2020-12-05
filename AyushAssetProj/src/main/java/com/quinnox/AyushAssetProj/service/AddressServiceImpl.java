package com.quinnox.AyushAssetProj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.AyushAssetProj.dao.AddressDao;
import com.quinnox.AyushAssetProj.entities.Address;
import com.quinnox.AyushAssetProj.entities.User;


@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao addrDao;
	
	@Override
	public Address findByUser(User user) {
		return addrDao.findByUser(user);
	}

	@Override
	public Address saveAndFlush(Address address) {
		return addrDao.saveAndFlush(address);
	}

	



	

}
