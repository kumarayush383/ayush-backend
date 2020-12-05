package com.quinnox.AyushAssetProj.service;

import java.util.List;

import com.quinnox.AyushAssetProj.entities.PlaceOrder;

public interface OrderService {


public	PlaceOrder findByOrderId(int orderId);

public	List<PlaceOrder> findAll();

public PlaceOrder save(PlaceOrder pc);


}
