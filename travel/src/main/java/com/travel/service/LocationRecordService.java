package com.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.bean.LocationRecord;
import com.travel.dao.LocationRecordMapper;

@Service
public class LocationRecordService {

	@Autowired
	LocationRecordMapper locationRecordMapper;

	public int addLocationRecord(long userid, double latitude, double longitude) {
		LocationRecord locationRecord = new LocationRecord();
		locationRecord.setLatitude(latitude);
		locationRecord.setLongitude(longitude);
		locationRecord.setUserid(userid);
		return locationRecordMapper.addRecord(locationRecord);
	}

	public LocationRecord getLastLocationRecordByUserId(long userid) {
			return locationRecordMapper.getLastLocation(userid);
	}

	public List<LocationRecord> getOnesLocatonRecord(long userid, String starttime, String endtime) {
			return locationRecordMapper.getLocationRecordByUserId(userid, starttime, endtime);
	}
	
	
}
