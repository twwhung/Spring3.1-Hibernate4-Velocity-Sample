package com.imob.daoimpls;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.imob.daos.AnalyzeDao;
import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;
@Repository("analyzeDaoImp1")
public class AnalyzeDaoImpl extends BasicDaoImpl implements AnalyzeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PairSummary> listPairSummary(int gid) {
		
		String subSQL = "SELECT winpid1 AS pid1, winpid2 AS pid2, 1 AS win, 0 AS lose, winscore - losescore AS diff FROM games WHERE gid = :gid UNION ALL SELECT losepid1 AS pid1, losepid2 AS pid2, 0 AS win, 1 AS lose, losescore - winscore AS diff FROM games WHERE gid = :gid ";
		String sql = "SELECT concat( pid1, concat( 'pair', pid2 ) ) AS pair,pid1, pid2, SUM( win ) AS wincount, SUM( lose ) AS losecount, TRUNCATE(AVG( diff ),1) AS avgdiff FROM (" 
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
}
