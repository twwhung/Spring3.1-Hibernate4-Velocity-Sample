package com.imob.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.imob.daoimpls.GameDaoImpl;

import com.imob.domains.Game;


@Transactional
@Service("gameService")
public class GameService {
	@Value("#{gameDaoImp1}")
	private GameDaoImpl gameDaoImpl;
	@CacheEvict(value= {"cacheGames", "cacheSingleSummarys","cachePairSummarys"}, key="'cacheGames'+ #gid")
	public void addGame(Game game){		
		gameDaoImpl.addGame(game);		
	}
	@CacheEvict(value= {"cacheGames", "cacheSingleSummarys","cachePairSummarys"}, key="'cacheGames'+ #gid")
	public void deleteGame(int id,int gid){
		gameDaoImpl.deleteGame(id,gid);
	}
	@Cacheable(value= "cacheGames", key="'cacheGames'+ #gid")
	public List<Game> listGames(int gid){
		return gameDaoImpl.listGame(gid);
	}	
}
