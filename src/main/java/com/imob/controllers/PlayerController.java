package com.imob.controllers;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import java.util.List;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;


import com.imob.commons.Ajax;
import com.imob.domains.Player;

import com.imob.services.PlayerService;

@Controller
public class PlayerController {
	
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
	
	
	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST, 
			produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> uploadImage(@RequestParam("file") MultipartFile file
			,@RequestParam("id") int id,HttpServletRequest request) throws IOException{				
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileType = file.getContentType();
		boolean success = true;
		if (file.isEmpty() || !fileType.equals("image/jpeg")){
			success = false;
		}else{		
			InputStream inputStream = file.getInputStream();
			OutputStream outputStream = new FileOutputStream(realPath + "resources/images/" + String.valueOf(id) + ".jpg");
			int curSize = (int) file.getSize();
			int readBytes = 0;
		    byte[] buffer = new byte[curSize];
		    while ((readBytes = inputStream.read(buffer, 0, curSize)) != -1) {			
                outputStream.write(buffer, 0, readBytes);
            }			
			outputStream.close();			
			inputStream.close();	
		}		
		
		return success ? Ajax.buildSuccessResult() : Ajax.buildErrorResult("The type of files could only be .jpg");						
	}
	
	@RequestMapping(value = "/addplayer", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> addPlayer(@RequestParam("name") String name) {		
		return Ajax.buildSuccessResult(playerService.addPlayer(gid, name));				
	}
	@RequestMapping(value = "/updateplayer", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> updatePlayer(@RequestParam("id") int id,@RequestParam("name") String name) {
		playerService.updatePlayer(id, gid, name);			
		return Ajax.buildSuccessResult();				
	}
}
