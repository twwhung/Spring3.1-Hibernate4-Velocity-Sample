package com.imob.daos;

import java.util.List;

import com.imob.domains.Player;

public interface PlayerDao {
	public void updatePlayer(Player player);
	public void savePlayer(Player player);
	public List<Player> listUser(int gid);	
	//Currently this function is only for Unit Test
	public void deletePlayer(int id);
}
