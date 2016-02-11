package com.apceps.service.cg.impl;

import com.apceps.domain.UserLocation;
import com.apceps.dao.cg.UserLocationDaoBase;
import com.apceps.dao.cg.impl.UserLocationDaoBaseImpl;
import com.apceps.service.cg.UserLocationServiceBase;
import com.apceps.domain.UserLocation;
import java.util.List;

public class UserLocationServiceBaseImpl implements UserLocationServiceBase {

	private UserLocationDaoBase userLocationDaoBase = null; 

	public UserLocationDaoBase getUserLocationDaoBase() {
		return userLocationDaoBase;
	}

	public void setUserLocationDaoBase(UserLocationDaoBase userLocationDaoBase) {
		this.userLocationDaoBase = userLocationDaoBase;
	}

	public UserLocationServiceBaseImpl() {
		this.userLocationDaoBase=new UserLocationDaoBaseImpl();
	}

	public UserLocation loadUserLocationByUserlocId(Long userlocId) throws Exception{
		return userLocationDaoBase.loadUserLocationByUserlocId(userlocId);
	}

	public void deleteUserLocationByUserlocId(Long userlocId) throws Exception{
		userLocationDaoBase.deleteUserLocationByUserlocId(userlocId);
	}

	public List<UserLocation> loadUserLocationByUserId(Long userId) throws Exception{
		return userLocationDaoBase.loadUserLocationByUserId(userId);
	}

	public void deleteUserLocationByUserId(Long userId) throws Exception{
		userLocationDaoBase.deleteUserLocationByUserId(userId);
	}

	public List<UserLocation> loadUserLocationByLocationId(Long locationId) throws Exception{
		return userLocationDaoBase.loadUserLocationByLocationId(locationId);
	}

	public void deleteUserLocationByLocationId(Long locationId) throws Exception{
		userLocationDaoBase.deleteUserLocationByLocationId(locationId);
	}

	public List<UserLocation> loadAllUserLocations() throws Exception{
		return userLocationDaoBase.loadAllUserLocations();
	}

	public Long addUserLocation(UserLocation userLocation) throws Exception{
		return userLocationDaoBase.addUserLocation(userLocation);
	}

	public void updateUserLocation(UserLocation userLocation) throws Exception{
		userLocationDaoBase.updateUserLocation(userLocation);
	}

}