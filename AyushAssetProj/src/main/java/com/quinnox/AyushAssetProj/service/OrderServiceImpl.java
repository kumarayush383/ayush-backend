package com.quinnox.AyushAssetProj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.AyushAssetProj.dao.OrderDao;
import com.quinnox.AyushAssetProj.entities.PlaceOrder;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao ordDao;
	
	@Override
	public PlaceOrder findByOrderId(int orderId) {
		
		return ordDao.findByOrderId(orderId);
	}

	@Override
	public List<PlaceOrder> findAll() {
		
		return ordDao.findAll();
	}

	@Override
	public PlaceOrder save(PlaceOrder pc) {
		return ordDao.save(pc);
		
		
	}

	

}