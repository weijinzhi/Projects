package com.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.travel.bean.LocationRecord;

public interface LocationRecordMapper {
	@Insert("insert into t_location_record (userid,longitude,latitude,createtime) values (#{userid},#{longitude},#{latitude},now())")
	public int addRecord(LocationRecord record);
	
	@Select("select * from t_location_record where userid = #{0} order by id desc limit 1")
	public LocationRecord getLastLocation(long userid);
	
//	@Select("select * from t_location_record where userId = #{0} and createTime between #{1} and #{2} group by userId,latitude,longitude order by id")
//	public List<LocationRecord> getLocationRecordByUserId(BigInteger userId,String startTime,String endTime);
	
	@Select("select * from t_location_record where userid = #{0} and createtime between #{1} and #{2} order by id")
	public List<LocationRecord> getLocationRecordByUserId(long userid,String starttTime,String endtime);
}