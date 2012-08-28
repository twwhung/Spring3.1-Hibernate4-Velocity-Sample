package com.imob.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.imob.daoimpls.AnalyzeDaoImpl;
import com.imob.domains.AttendanceStat;
import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;



@Transactional
@Service("analyzeService")
public class AnalyzeService {
	@Value("#{analyzeDaoImp1}")
	private AnalyzeDaoImpl analyzeDaoImpl;
	@Cacheable(value= "cachePairSummarys", key="'cacheGames'+ #gid")
	public List<PairSummary> listPairSummarys(int gid){
		return analyzeDaoImpl.listPairSummary(gid);
	}
	@Cacheable(value= "cacheSingleSummarys", key="'cacheGames'+ #gid")
	public List<SingleSummary> listSingleSummarys(int gid){
		return analyzeDaoImpl.listSingleSummary(gid);
	}	
	@Cacheable(value= "cacheAttendanceSummarys", key="'cacheAttendance'+ #gid")
	public List<AttendanceStat> listAttendanceSummary(int gid){
		return analyzeDaoImpl.listAttendanceSummary(gid);
	}
	
	public Map<String,Object> getSingleStat(int gid, int pid){
		Map<String,Object> singleMap = new HashMap<String,Object>();
		singleMap.put("attendance", analyzeDaoImpl.getSingleAttendanceStat(gid, pid));
		singleMap.put("games", analyzeDaoImpl.getSingleGamesStat(pid));
		singleMap.put("gpd", analyzeDaoImpl.getSingleGamesPerDate(pid));
		singleMap.put("teamate", analyzeDaoImpl.getSingleTeamateStat(pid));
		
		
		return singleMap;
	}
}
