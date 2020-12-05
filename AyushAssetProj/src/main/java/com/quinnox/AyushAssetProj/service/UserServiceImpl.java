package com.quinnox.AyushAssetProj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinnox.AyushAssetProj.dao.UserDao;
import com.quinnox.AyushAssetProj.entities.User;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	@Override
	public User findByEmailAndPasswordAndUsertype(String email, String password, String usertype) {
		
		return userDao.findByEmailAndPasswordAndUsertype(email, password, usertype);
	}
	@Override
	public User save(User user) {
		
		return userDao.save(user);
	}
	

}
