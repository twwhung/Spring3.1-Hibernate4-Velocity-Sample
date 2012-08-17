package com.imob.controllers;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

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


import com.imob.domains.Player;

import com.imob.services.PlayerService;

@Controller
public class PlayerController extends BasicController{
	
	private static final int gid = 1;
	
	@Value("#{playerService}")
	private PlayerService playerService;
	
	@RequestMapping(value = "/players.show", method = RequestMethod.GET)
	public String players(Model model) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Player> memberList = playerService.listPlayers(gid);				
		String membersJSONString = mapper.writeValueAsString(memberList);				
		model.addAttribute("memberJSONList",membersJSONString);				
		return "players";
	}	
	@RequestMapping(value = "/addplayer", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> addPlayer(@RequestParam("name") String name) {		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");
		result.put("value", playerService.addPlayer(gid, name));
		return result;				
	}
	@RequestMapping(value = "/updateplayer", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> updatePlayer(@RequestParam("id") int id,@RequestParam("name") String name) {
		playerService.updatePlayer(id, gid, name);		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");		
		return result;				
	}
}
