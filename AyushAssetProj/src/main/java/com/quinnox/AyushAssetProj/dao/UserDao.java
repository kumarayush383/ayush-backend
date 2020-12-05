package com.quinnox.AyushAssetProj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quinnox.AyushAssetProj.entities.User;
@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long>{
	public 	User findByEmailAndPasswordAndUsertype(String email, String password, String usertype);
}
