package com.imob.services;


import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
	public void saveAttendance(List<Attendance> aList){								
		attendanceDaoImp1.saveAttendance(aList);
	}
	public List<AttendanceSummary>listSummary(int gid){
		return attendanceDaoImp1.listSummary(gid);
	}
	public void deleteAttendance(int gid,int pid,String dateString) throws ParseException{
		attendanceDaoImp1.deleteAttendance(gid,pid,dateString);
	}
}
