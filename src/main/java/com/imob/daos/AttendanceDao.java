package com.imob.daos;

import java.text.ParseException;
import java.util.List;

import com.imob.domains.Attendance;
import com.imob.domains.AttendanceSummary;


public interface AttendanceDao {
	public void saveAttendance(List<Attendance> attendanceList);
	public List<AttendanceSummary> listSummary(int gid);
	public void deleteAttendance(int gid,int pid, String dateString) throws ParseException;
	
}
