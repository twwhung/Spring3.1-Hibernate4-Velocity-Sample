package com.imob.controllers;

import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
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

import com.imob.commons.BeanList;
import com.imob.domains.Attendance;
import com.imob.domains.AttendanceStat;
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
		model.addAttribute("attendanceSummaryJSONList", buildAttendanceSummaryJSONString());		
		return "attendance";
	}
	@RequestMapping(value = "/attendance_stat.show", method = RequestMethod.GET)
	public String attendanceStat(Model model) throws JsonGenerationException, JsonMappingException, IOException{
		List<AttendanceStat> attendanceStatList = attendanceService.listStat(gid);
		ObjectMapper mapper = new ObjectMapper();		
		model.addAttribute("attendanceStatJSONList", mapper.writeValueAsString(attendanceStatList));
		return "attendance_stat";
	}
	
	@RequestMapping(value = "/saveattendance", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> saveAttendance(@BeanList(base="pid") ArrayList<Attendance> aList) throws JsonGenerationException, JsonMappingException, IOException {				
		for (Attendance attendance:aList){
			attendance.setGid(gid);
		}		
		attendanceService.saveAttendance(aList);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");	
		result.put("value",attendanceService.listSummary(gid));
		return result;				
	}
	@RequestMapping(value = "/deleteattendance", method = RequestMethod.POST, produces="application/json", headers="X-Requested-With=XMLHttpRequest")
	@ResponseBody public Map<String,Object> deleteAttendance(@RequestParam("date") String date, @RequestParam("pid") int pid) throws ParseException, JsonGenerationException, JsonMappingException, IOException {						
		Date deleteDate =Attendance.buildDate(date); 
		attendanceService.deleteAttendance(gid,pid,deleteDate);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", "ok");
		result.put("value", attendanceService.listSummary(gid));
		return result;				
	}
	
	private String buildAttendanceSummaryJSONString() throws JsonGenerationException, JsonMappingException, IOException{
		List<AttendanceSummary> attendanceSummaryList = attendanceService.listSummary(gid);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(attendanceSummaryList);
		
	}
}
