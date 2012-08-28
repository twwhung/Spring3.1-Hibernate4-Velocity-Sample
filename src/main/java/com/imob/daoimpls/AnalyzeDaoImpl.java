package com.imob.daoimpls;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.imob.daos.AnalyzeDao;
import com.imob.domains.AttendanceStat;
import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;
@Repository("analyzeDaoImp1")
public class AnalyzeDaoImpl extends BasicDaoImpl implements AnalyzeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PairSummary> listPairSummary(int gid) {
		
		String subSQL = "SELECT winpid1 AS pid1, winpid2 AS pid2, 1 AS win, 0 AS lose, winscore - losescore AS diff FROM games WHERE gid = :gid UNION ALL SELECT losepid1 AS pid1, losepid2 AS pid2, 0 AS win, 1 AS lose, losescore - winscore AS diff FROM games WHERE gid = :gid ";
		String sql = "SELECT pid1, pid2, SUM( win ) AS wincount, SUM( lose ) AS losecount, TRUNCATE(AVG( diff ),1) AS avgdiff FROM (" 
			+ subSQL + 
			") AS TMP GROUP BY pid1, pid2";
		Query query = getCurrentSession().createSQLQuery(sql)
		.addEntity(PairSummary.class)
		.setParameter("gid", gid);						
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SingleSummary> listSingleSummary(int gid) {
		String subSQL = "SELECT  winpid1 as pid , 1 as win , 0 as lose, winscore - losescore as diff FROM `games` WHERE gid = :gid union all SELECT  winpid2 as pid , 1 as win , 0 as lose, winscore - losescore as diff FROM `games` WHERE gid = :gid union all SELECT  losepid1 as pid , 0 as win , 1 as lose, losescore - winscore as diff FROM `games` WHERE gid = :gid union all SELECT  losepid2 as pid , 0 as win , 1 as lose, losescore - winscore as diff FROM `games` WHERE gid = :gid ";
		String sql = "Select pid, sum(win) as wincount, sum(lose) as losecount, TRUNCATE(AVG( diff ),1) as avgdiff from (" 
			+ subSQL + 
			") as tmp group by pid";
		Query query = getCurrentSession().createSQLQuery(sql)
		.addEntity(SingleSummary.class)
		.setParameter("gid", gid);
				
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceStat> listAttendanceSummary(int gid) {
		String sql = "SELECT pid, TRUNCATE( AVG( late ) , 1 ) AS avglate, SUM( late ) AS sumlate, COUNT( * ) AS count FROM attendance WHERE gid =:gid GROUP BY pid ";
		Query query = getCurrentSession().createSQLQuery(sql)
				.addEntity(AttendanceStat.class)
				.setParameter("gid", gid);
				
		return query.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSingleAttendanceStat(int gid, int pid){
		String sql = "Select *, TRUNCATE(count/total,1) as per FROM (SELECT TRUNCATE( AVG( late ) , 1 ) AS avglate, COUNT( * ) AS count FROM attendance WHERE pid =:pid GROUP BY pid ) as A , (SELECT COUNT(Distinct date) as total from attendance where gid =:gid) as B";
		Query query = getCurrentSession().createSQLQuery(sql)
			.setParameter("gid", gid)
			.setParameter("pid", pid)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("rawtypes")
		List tmpList = query.list();
		if (tmpList.size() == 0){
			return null;
		}else{
			return (Map<String, Object>) tmpList.get(0);
		}
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSingleGamesStat(int pid){
		String sql = "SELECT COUNT( IF( winpid1 = :pid OR winpid2 =:pid, 1, NULL ) ) AS wincount, COUNT( * ) AS total, SUM( IF( winpid1 =:pid OR winpid2 =:pid, winscore, losescore ) ) AS myscore, SUM( IF( losepid1 =:pid OR losepid1 =:pid , winscore, losescore ) ) AS counterscore FROM `games` WHERE winpid1 =:pid OR winpid2 =:pid OR losepid1 =:pid OR losepid2 =:pid ";
		Query query = getCurrentSession().createSQLQuery(sql)
			.setParameter("pid", pid)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("rawtypes")
		List tmpList = query.list();
		if (tmpList.size() == 0){
			return null;
		}else{
			return (Map<String, Object>) tmpList.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public double getSingleGamesPerDate(int pid){
		String sql = "SELECT TRUNCATE( AVG( count ) , 1 ) AS gpd FROM ( SELECT COUNT( * ) AS count FROM games WHERE winpid1 =:pid  OR winpid2 =:pid  OR losepid1 =:pid OR  losepid2 =:pid GROUP BY date ) AS A";
		Query query = getCurrentSession().createSQLQuery(sql)
			.setParameter("pid", pid)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("rawtypes")
		List tmpList = query.list();
		
		if (tmpList.size() == 0){
			return 0;
		}else{	
			BigDecimal gpd  = (BigDecimal) ((Map<String, Object>) tmpList.get(0)).get("gpd");
			return gpd == null ? 0 : gpd.doubleValue();			
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSingleTeamateStat(int pid){
		String sql = "SELECT pid , sum(wincount)/(sum(wincount) + sum(losecount)) as per FROM ( select pid, count(*) as wincount , 0 as losecount From (select IF(winpid1 = :pid, winpid2,winpid1) as pid  from games where winpid1=:pid or winpid2=:pid) as A group by pid UNION ALL select pid, 0 as wincount , count(*) as losecount From (select IF(losepid1 = :pid, losepid2,losepid1) as pid  from games where losepid1=:pid or losepid2=:pid) as B group by pid )  as C Group by pid order by per";
		Query query = getCurrentSession().createSQLQuery(sql)
			.setParameter("pid", pid)
			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> tmpList = query.list();
		if (tmpList.size() == 0){
			return null;
		}
		Map<String, Object> TeamateMap = new HashMap<String, Object>();
		Map<String, Object> bestMate = (Map<String, Object>) tmpList.get(0); 
		TeamateMap.put("bestpid", bestMate.get("pid"));
		TeamateMap.put("bestper", bestMate.get("per"));
		
		Map<String, Object> worstMate = (Map<String, Object>) tmpList.get(tmpList.size()-1); 
		TeamateMap.put("worstpid", worstMate.get("pid"));
		TeamateMap.put("worstper", worstMate.get("per"));
		
		return TeamateMap;
	} 

	
	
}
