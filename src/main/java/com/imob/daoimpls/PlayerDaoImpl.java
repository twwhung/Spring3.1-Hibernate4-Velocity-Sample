package com.imob.daoimpls;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;



import com.imob.daos.PlayerDao;
import com.imob.domains.Player;

@Repository("playerDaoImp1")
@DynamicUpdate
public class PlayerDaoImpl extends BasicDaoImpl implements PlayerDao{
	public void savePlayer(Player player) {
		getCurrentSession().saveOrUpdate(player);
	}
	@SuppressWarnings("unchecked")
	public List<Player> listUser(int gid) {
				
		Query query = getCurrentSession().createQuery("from Player where gid =:gid"); 
		query.setParameter("gid", gid);				
		return query.list();
	}
	public void updatePlayer(Player player) {
		getCurrentSession().update(player);	
	}
	
		
	//Currently this function is only for Unit Test
	public void deletePlayer(int id){
		Query query = getCurrentSession().createQuery("delete from Player where id =:id ");
		query.setParameter("id", id);		
		query.executeUpdate();
	}
	@Override
	public Player getPlayer(int id) {
		
		return (Player)getCurrentSession().get(Player.class, id);
	}
}