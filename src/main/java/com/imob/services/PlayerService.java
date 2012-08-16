package com.imob.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imob.daoimpls.PlayerDaoImpl;
import com.imob.domains.Player;

@Transactional
@Service("playerService")
public class PlayerService {
	@Value("#{playerDaoImp1}")
	private PlayerDaoImpl playerDaoImpl;
	public Player addPlayer(int gid, String name){
		Player player = new Player();
		player.setGid(gid);
		player.setName(name);
		playerDaoImpl.savePlayer(player);
		return player;
	}
	public void updatePlayer(int id,int gid,String name){
		Player player = new Player();
		player.setId(id);
		player.setName(name);		
		playerDaoImpl.updatePlayer(player);	
	}
	public List<Player> listPlayers(int gid){
		return playerDaoImpl.listUser(gid);
	}
}
