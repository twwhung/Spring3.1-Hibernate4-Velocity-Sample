package com.imob.daoimpls;




import java.util.List;

import org.hibernate.Query;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;

import com.imob.daos.GameDao;

import com.imob.domains.Game;




@Repository("gameDaoImp1")
@DynamicUpdate
public class GameDaoImpl extends BasicDaoImpl implements GameDao{
	@Override
	public void addGame(Game game) {
		getCurrentSession().saveOrUpdate(game);		
	}
	@Override
	public void deleteGame(int id,int gid){
		
		Query query = getCurrentSession().createQuery("delete from Game where id =:id and gid=:gid");
		query.setParameter("id", id);
		query.setParameter("gid", gid);
		query.executeUpdate();
				
	}	
	@SuppressWarnings("unchecked")
	public List<Game> listGame(int gid) {
		Query query = getCurrentSession().createQuery("from Game where gid =:gid"); 
		query.setParameter("gid", gid);				
		return query.list();
	}
}