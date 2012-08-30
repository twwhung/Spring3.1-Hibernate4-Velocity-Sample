package com.imob.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class AttendanceStat {
	private int pid;
	private double avglate;
	private int sumlate;
	private int count;
	
	
	@Id	
	public int getPid(){		
		
		return pid;
	}
	
	public void setPid(int pid){
		
		
		this.pid = pid;
	}		
	
	@Column
	public double getAvglate(){
		return avglate;
	}
	public void setAvglate(double avglate){
		this.avglate = avglate;
	}
	@Column
	public int getSumlate(){
		return sumlate;
	}
	public void setSumlate(int sumlate){
		this.sumlate = sumlate;			
	}
	@Column
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count = count;
	}	
}
