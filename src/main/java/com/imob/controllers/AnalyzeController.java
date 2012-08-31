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




import com.imob.domains.AttendanceStat;
import com.imob.domains.PairSummary;
import com.imob.domains.Player;
import com.imob.domains.SingleSummary;
import com.imob.services.AnalyzeService;

import com.imob.services.PlayerService;




@Controller
public class AnalyzeController {
	private static final int gid = 1;
	
	@Value("#{analyzeService}")
	private AnalyzeService analyzeService;
	@Value("#{playerService}")
	private PlayerService playerService;
	
	
	@RequestMapping(value = "/analyzes.show")
	public String players(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		
		
		List<AttendanceStat> attendanceStatList = analyzeService.listAttendanceSummary(gid);
		ObjectMapper mapper = new ObjectMapper();		
		model.addAttribute("attendanceStatJSONList", mapper.writeValueAsString(attendanceStatList));
		
		
		List<PairSummary> pairSummaryList = analyzeService.listPairSummarys(gid);		
		mapper = new ObjectMapper();
		String pairSummaryJSONString = mapper.writeValueAsString(pairSummaryList);
		model.addAttribute("pairSummaryJSONList", pairSummaryJSONString);
		
		List<SingleSummary> singleSummaryList = analyzeService.listSingleSummarys(gid);		
		//ObjectMapper mapper = new ObjectMapper();
		String singleSummaryJSONString = mapper.writeValueAsString(singleSummaryList);
		model.addAttribute("singleSummaryJSONList", singleSummaryJSONString);
				
		List<Player> playerList = playerService.listPlayers(gid);
		model.addAttribute("playerList", playerList);
		return "analyzes";
	}	
	@RequestMapping(value = "/getsinglestat", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> getSingleStat(@RequestParam("pid") int pid) {				
		;
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");	
		result.put("value",analyzeService.getSingleStat(gid, pid));
		return result;				
	}
	
	
}
