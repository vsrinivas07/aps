package com.apceps.service.cg.impl;

import com.apceps.domain.User;
import com.apceps.dao.cg.UserDaoBase;
import com.apceps.dao.cg.impl.UserDaoBaseImpl;
import com.apceps.service.cg.UserServiceBase;
import com.apceps.domain.User;
import java.util.List;

public class UserServiceBaseImpl implements UserServiceBase {

	private UserDaoBase userDaoBase = null; 

	public UserDaoBase getUserDaoBase() {
		return userDaoBase;
	}

	public void setUserDaoBase(UserDaoBase userDaoBase) {
		this.userDaoBase = userDaoBase;
	}

	public UserServiceBaseImpl() {
		this.userDaoBase=new UserDaoBaseImpl();
	}

	public User loadUserByUserId(Long userId) throws Exception{
		return userDaoBase.loadUserByUserId(userId);
	}

	public void deleteUserByUserId(Long userId) throws Exception{
		userDaoBase.deleteUserByUserId(userId);
	}

	public User loadUserByUserName(String userName) throws Exception{
		return userDaoBase.loadUserByUserName(userName);
	}

	public void deleteUserByUserName(String userName) throws Exception{
		userDaoBase.deleteUserByUserName(userName);
	}

	public List<User> loadUserByRoleId(Long roleId) throws Exception{
		return userDaoBase.loadUserByRoleId(roleId);
	}

	public void deleteUserByRoleId(Long roleId) throws Exception{
		userDaoBase.deleteUserByRoleId(roleId);
	}

	public List<User> loadAllUsers() throws Exception{
		return userDaoBase.loadAllUsers();
	}

	public Long addUser(User user) throws Exception{
		return userDaoBase.addUser(user);
	}

	public void updateUser(User user) throws Exception{
		userDaoBase.updateUser(user);
	}

}