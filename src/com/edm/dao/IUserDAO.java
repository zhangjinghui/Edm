package com.edm.dao;

import com.edm.vo.User;

public interface IUserDAO {
	User login(User user);

	boolean register(User user);

	public boolean query(String username);
	
	
}
