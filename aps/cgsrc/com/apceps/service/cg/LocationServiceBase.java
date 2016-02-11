package com.apceps.service.cg;

import com.apceps.domain.Location;
import com.apceps.domain.cg.LocationBase;
import java.util.List;

public interface LocationServiceBase {

	public Location loadLocationByLocationId(Long locationId) throws Exception;

	public void deleteLocationByLocationId(Long locationId) throws Exception;

	public List<Location> loadAllLocations() throws Exception;

	public Long addLocation(Location location) throws Exception;

	public void updateLocation(Location location) throws Exception;

}