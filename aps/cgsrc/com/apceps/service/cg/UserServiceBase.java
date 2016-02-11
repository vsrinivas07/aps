package com.apceps.service.cg;

import com.apceps.domain.User;
import com.apceps.domain.cg.UserBase;
import java.util.List;

public interface UserServiceBase {

	public User loadUserByUserId(Long userId) throws Exception;

	public void deleteUserByUserId(Long userId) throws Exception;

	public User loadUserByUserName(String userName) throws Exception;

	public void deleteUserByUserName(String userName) throws Exception;

	public List<User> loadUserByRoleId(Long roleId) throws Exception;

	public void deleteUserByRoleId(Long roleId) throws Exception;

	public List<User> loadAllUsers() throws Exception;

	public Long addUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

}