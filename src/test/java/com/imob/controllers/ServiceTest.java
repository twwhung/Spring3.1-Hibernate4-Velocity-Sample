package com.imob.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.imob.domains.Player;

import com.imob.services.PlayerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ServiceTest {
	private int gid = 9999;
	@Autowired
	private PlayerService playerService;
	
	
	
	@Test
	public void playerServiceTest(){		
		int prevCount = playerService.listPlayers(gid).size();
		Assert.assertEquals(0,prevCount);						
		Player player = playerService.addPlayer(gid, "testuser");				
		int currCount = playerService.listPlayers(gid).size();;		
		Assert.assertEquals(1,currCount);	
		playerService.deletePlayer(player.getId());				
		currCount = playerService.listPlayers(gid).size();;			
		Assert.assertEquals(0,prevCount);										
	}
						
	
}
