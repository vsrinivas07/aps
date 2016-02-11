package com.apceps.domain.cg;

public class UserLocationBase {

	private Long userlocId = null; 

	private Long userId = null; 

	private Long locationId = null; 

	private String locationName = null; 

	public Long getUserlocId() {
		return userlocId;
	}

	public void setUserlocId(Long userlocId) {
		this.userlocId = userlocId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}