package com.imob.daos;

import java.util.List;
import java.util.Map;

import com.imob.domains.AttendanceStat;
import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;



public interface AnalyzeDao {
	public List<PairSummary> listPairSummary(final int gid);
	public List<SingleSummary> listSingleSummary(final int gid);
	public List<AttendanceStat> listAttendanceSummary(int gid);
	public Map<String, Object> getSingleAttendanceStat(int gid, int pid);
	public Map<String, Object> getSingleGamesStat(int pid);
	public double getSingleGamesPerDate(int pid);
	public Map<String, Object> getSingleTeamateStat(int pid);
}
