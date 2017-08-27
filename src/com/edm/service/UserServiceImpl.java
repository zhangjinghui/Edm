package com.edm.service;

import com.edm.dao.IUserDAO;
import com.edm.dao.UserDAOImpl;
import com.edm.vo.User;

public class UserServiceImpl implements IUserService {
	private IUserDAO dao = new UserDAOImpl();

	public User login(User user) {
		return dao.login(user);
	}

	public boolean register(User user) {
		return dao.register(user);
	}

	public boolean query(String username) {
		return dao.query(username);
	}

}
