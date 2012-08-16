package com.imob.daos;

import com.imob.domains.Game;

public interface GameDao {
	public void addGame(Game game);
	public void deleteGame(int id,int gid);
}
