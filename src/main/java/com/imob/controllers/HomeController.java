package com.imob.controllers;





import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.imob.domains.Player;
import com.imob.services.PlayerService;


@Controller
public class HomeController {						
	private static final int gid = 1;
	@Value("#{playerService}")
	private PlayerService playerService;
	
	@RequestMapping(value="/", method={RequestMethod.GET})
	public ModelAndView home(HttpSession session) throws Exception {												
		return new ModelAndView("forward:/analyzes.show");
	}
	@RequestMapping(value="/whoami", method={RequestMethod.GET})
	public ModelAndView whoAmI(HttpSession session,Model model) throws Exception {												
		List<Player> playerList = playerService.listPlayers(gid);
		model.addAttribute("playerList", playerList);
		return new ModelAndView("home");
	}
	
	
	
	
}
