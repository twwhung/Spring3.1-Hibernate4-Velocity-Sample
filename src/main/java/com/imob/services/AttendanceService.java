package com.imob.services;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.imob.daoimpls.AttendanceDaoImpl;
import com.imob.domains.Attendance;
import com.imob.domains.AttendanceSummary;


@Transactional
@Service("attendanceService")
public class AttendanceService {
	@Value("#{attendanceDaoImp1}")
	private AttendanceDaoImpl attendanceDaoImp1;
	
	@CacheEvict(value= {"cacheAttendance", "cacheAttendanceSummarys"}, key="'cacheAttendance'+ #gid")
	public void saveAttendance(List<Attendance> aList,int gid){								
		attendanceDaoImp1.saveAttendance(aList);
	}
	@Cacheable(value= "cacheAttendance", key="'cacheAttendance'+ #gid")
	public List<AttendanceSummary>listSummary(int gid){
		return attendanceDaoImp1.listSummary(gid);
	}
	@CacheEvict(value= {"cacheAttendance", "cacheAttendanceSummarys"}, key="'cacheAttendance'+ #gid")
	public void deleteAttendance(int gid,int pid,Date date) throws ParseException{
		attendanceDaoImp1.deleteAttendance(gid,pid,date);
	}	
}
