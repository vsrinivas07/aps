package com.apceps.service.cg.impl;

import com.apceps.domain.Location;
import com.apceps.dao.cg.LocationDaoBase;
import com.apceps.dao.cg.impl.LocationDaoBaseImpl;
import com.apceps.service.cg.LocationServiceBase;
import com.apceps.domain.Location;
import java.util.List;

public class LocationServiceBaseImpl implements LocationServiceBase {

	private LocationDaoBase locationDaoBase = null; 

	public LocationDaoBase getLocationDaoBase() {
		return locationDaoBase;
	}

	public void setLocationDaoBase(LocationDaoBase locationDaoBase) {
		this.locationDaoBase = locationDaoBase;
	}

	public LocationServiceBaseImpl() {
		this.locationDaoBase=new LocationDaoBaseImpl();
	}

	public Location loadLocationByLocationId(Long locationId) throws Exception{
		return locationDaoBase.loadLocationByLocationId(locationId);
	}

	public void deleteLocationByLocationId(Long locationId) throws Exception{
		locationDaoBase.deleteLocationByLocationId(locationId);
	}

	public List<Location> loadAllLocations() throws Exception{
		return locationDaoBase.loadAllLocations();
	}

	public Long addLocation(Location location) throws Exception{
		return locationDaoBase.addLocation(location);
	}

	public void updateLocation(Location location) throws Exception{
		locationDaoBase.updateLocation(location);
	}

}