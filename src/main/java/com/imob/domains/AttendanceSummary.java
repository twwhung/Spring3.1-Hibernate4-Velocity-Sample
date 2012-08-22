package com.imob.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AttendanceSummary {
	private String dateString;
	private int total;
	private String info;
	
	@Id		
	@Column(name="date")
	public String getDate(){
		return dateString;
	}
	public void setDate(String dateString){
		this.dateString = dateString;
	}
	@Column(name="total")
	public int getTotal(){
		return this.total;
	}
	public void setTotal(int total){
		this.total = total;
	}
	@Column(name="info")
	public String getInfo(){
		return info;
	}
	public void setInfo(String info){
		this.info = info;
	}
}
