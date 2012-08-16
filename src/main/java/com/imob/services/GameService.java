package com.imob.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.imob.daoimpls.GameDaoImpl;

import com.imob.domains.Game;


@Transactional
@Service("gameService")
public class GameService {
	@Value("#{gameDaoImp1}")
	private GameDaoImpl gameDaoImpl;
	public Game addGame(Map<String,Object> gameConfig){
		Game game = createGame(gameConfig);
		gameDaoImpl.addGame(game);
		return game;
	}
	public void deleteGame(int id,int gid){
		gameDaoImpl.deleteGame(id,gid);
	}
	public List<Game> listGames(int gid){
		return gameDaoImpl.listGame(gid);
	}
	private Game createGame(Map<String,Object> gameConfig){
		Game game = new Game();
		if(gameConfig.containsKey("id")){
			game.setId(((Number)gameConfig.get("id")).intValue());
		}
		if(gameConfig.containsKey("gid")){
			game.setGid(((Number)gameConfig.get("gid")).intValue());
		}
		if(gameConfig.containsKey("type")){
			game.setType(((Number)gameConfig.get("type")).intValue());
		}
		if(gameConfig.containsKey("winpid1") && gameConfig.containsKey("winpid2")){
			int winpid1 = ((Number)gameConfig.get("winpid1")).intValue();
			int winpid2 = ((Number)gameConfig.get("winpid2")).intValue();
			if (winpid1 > winpid2){
				winpid1 += winpid2;
				winpid2= winpid1 - winpid2;
				winpid1 -= winpid2 ;
			}
			game.setWinPid1(winpid1);
			game.setWinPid2(winpid2);			
												
		}					
		if(gameConfig.containsKey("winscore")){
			game.setWinScore(((Number)gameConfig.get("winscore")).intValue());
		}
		
		if(gameConfig.containsKey("losepid1") && gameConfig.containsKey("losepid2")){
			int losepid1 = ((Number)gameConfig.get("losepid1")).intValue();
			int losepid2 = ((Number)gameConfig.get("losepid2")).intValue();
			if (losepid1 > losepid2){
				losepid1 += losepid2;
				losepid2= losepid1 - losepid2;
				losepid1 -= losepid2 ;
			}
			
			game.setLosePid1(losepid1);
			game.setLosePid2(losepid2);			
															
		}					
		if(gameConfig.containsKey("losescore")){
			game.setLoseScore(((Number)gameConfig.get("losescore")).intValue());
		}
		
		return game;		
	}
}
