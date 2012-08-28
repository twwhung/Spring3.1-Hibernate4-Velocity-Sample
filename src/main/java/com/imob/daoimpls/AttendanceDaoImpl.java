package com.imob.daoimpls;

import java.text.ParseException;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.imob.daos.AttendanceDao;

import com.imob.domains.Attendance;

import com.imob.domains.AttendanceSummary;

@Repository("attendanceDaoImp1")
public class AttendanceDaoImpl  extends BasicDaoImpl implements AttendanceDao{

	@Override
	public void saveAttendance(List<Attendance> attendanceList) {
		for(Attendance attendance:attendanceList){ 
			getCurrentSession().saveOrUpdate(attendance);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceSummary> listSummary(int gid) {
		
		String sql = "SELECT date , count(*) as total, GROUP_CONCAT(IF(late > 0 , CONCAT(name,CONCAT('(' , CONCAT(late,')'))),CONCAT(name,'(準時)') ) SEPARATOR ' ') as info FROM  attendance as a Inner JOIN players as p ON a.pid = p.id AND a.gid = :gid GROUP BY date";
		Query query = getCurrentSession().createSQLQuery(sql)
		.addEntity(AttendanceSummary.class)
		.setParameter("gid", gid);
				
		return query.list();
		
	}

	@Override
	public void deleteAttendance(int gid, int pid, Date date) throws ParseException {						
		Query query = getCurrentSession().createSQLQuery("delete from attendance where pid =:pid and gid=:gid and date =:date");
		query.setParameter("pid", pid);
		query.setParameter("gid", gid);
		query.setParameter("date", date);		
		query.executeUpdate();		
	}

	

}
