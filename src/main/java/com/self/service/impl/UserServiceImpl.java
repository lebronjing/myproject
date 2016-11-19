package com.self.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self.IDao.UserMapper;
import com.self.domain.User;
import com.self.service.UserService;

//@Service("userServiceImpl")
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String username, String password) {
		return this.userMapper.login(username, password);
	}

}
