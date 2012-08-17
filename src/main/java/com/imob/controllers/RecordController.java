package com.imob.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.imob.domains.Game;
import com.imob.domains.Player;

import com.imob.services.GameService;
import com.imob.services.PlayerService;
@Controller
public class RecordController extends BasicController{
	private static final int gid = 1;
	@Value("#{playerService}")
	private PlayerService playerService;
	@Value("#{gameService}")
	private GameService gameService;
	
	@RequestMapping(value = "/records.show", method = RequestMethod.GET)
	public String players(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		List<Game> gameList = gameService.listGames(gid); 		
		
		ObjectMapper mapper = new ObjectMapper();
		String gamesJSONString = mapper.writeValueAsString(gameList);
		model.addAttribute("gameJSONList", gamesJSONString);
		List<Player> playerList = playerService.listPlayers(gid);
		model.addAttribute("playerList", playerList);
		
		return "records";
	}
	@RequestMapping(value = "/adddoublerecord", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> addDoubleRecord(Game game) {								
		game.setGid(gid);
		game.setType(0);
		gameService.addGame(game);
						
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");
		result.put("value", game);
				
		return result;				
	}	
	@RequestMapping(value = "/deleterecord", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> deleteRecord(@RequestParam("id") int id) {
		gameService.deleteGame(id,gid);		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");		
		return result;				
	}
}
