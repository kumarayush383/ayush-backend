package com.quinnox.AyushAssetProj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quinnox.AyushAssetProj.entities.PlaceOrder;
@Repository
@Transactional
public interface OrderDao extends JpaRepository<PlaceOrder, Long>{
	PlaceOrder findByOrderId(int orderId);
	List<PlaceOrder> findAll();

}
