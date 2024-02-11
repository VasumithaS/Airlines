
package com.sigma.service;

import com.sigma.beans.Carrier;
import com.sigma.beans.User;
import com.sigma.dao.CarrierDAOArrayImplementation;
import com.sigma.dao.CarrierDAO;
import com.sigma.dao.UserDAO;
import com.sigma.dao.UserDAOArrayImplementation;

public class UserService{
	UserDAO userDao = new UserDAOArrayImplementation();
	public int addUser(User user)
	{
		userDao.addUserDetails(user);
		return 0;
	}

}

