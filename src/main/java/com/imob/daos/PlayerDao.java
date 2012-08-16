package com.imob.daos;

import java.util.List;

import com.imob.domains.Player;

public interface PlayerDao {
	public void updatePlayer(Player player);
	public void savePlayer(Player player);
	public List<Player> listUser(int gid);	
}
