package com.imob.controllers;

import java.io.IOException;
import java.util.ArrayList;
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

import org.springframework.web.bind.annotation.ResponseBody;

import com.imob.commons.BeanList;
import com.imob.domains.Attendance;
import com.imob.domains.AttendanceSummary;

import com.imob.domains.Player;
import com.imob.services.AttendanceService;
import com.imob.services.PlayerService;

@Controller
public class AttendanceController {
	private static final int gid = 1;
	@Value("#{playerService}")
	private PlayerService playerService;
	
	
	@Value("#{attendanceService}")
	private AttendanceService attendanceService;
	
	
	@RequestMapping(value = "/attendance.show", method = RequestMethod.GET)
	public String attendance(Model model) throws JsonGenerationException, JsonMappingException, IOException{
		List<Player> playerList = playerService.listPlayers(gid);
		model.addAttribute("playerList", playerList);
		
		List<AttendanceSummary> attendanceSummaryList = attendanceService.listSummary(gid);
		
		ObjectMapper mapper = new ObjectMapper();
		String attendanceSummaryJSONString = mapper.writeValueAsString(attendanceSummaryList);
		model.addAttribute("attendanceSummaryJSONList", attendanceSummaryJSONString);
		
		return "attendance";
	}
	
	@RequestMapping(value = "/saveattendance", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> saveAttendance(@BeanList(base="uid") ArrayList<Attendance> aList) {				
		for (Attendance attendance:aList){
			attendance.setGid(gid);
		}		
		attendanceService.saveAttendance(aList);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");		
		return result;				
	}
	@RequestMapping(value = "/deleteattendance", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> deleteAttendance(Attendance attendance) {						
		//attendanceService.deleteAttendance(attendance);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");		
		return result;				
	}
}
