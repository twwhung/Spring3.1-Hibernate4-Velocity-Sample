package com.imob.domains;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;
@Validated
@Entity
@Table(name="attendance")
public class Attendance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private int pid;
	private int gid;
	private int late;
	public static Date buildDate(String dateString){
		try{
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		}catch(Exception e){
			try {
				return  new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
			} catch (ParseException e1) {
				return null;
			}
		}				
	}
	@Id
	@Column(name="date")
	@Type(type="date")
	@NotNull
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
			this.date = date;				
	}	
	@Id
	@Min(1)
	@Column(name="pid")
	public int getPid(){
		return pid;
	}
	public void setPid(int pid){
		this.pid = pid;
	}
	@Min(1)
	@Column(name="gid", updatable = false)
	public int getGid(){
		return this.gid;
	}		
	public void setGid(int gid){
		this.gid = gid;		
	}
	
	@Min(0)
	@Column(name="late")
	public int getLate(){
		return late;
	}
	public void setLate(int late){
		this.late = late;
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null) return false;
		if(!(arg0 instanceof Attendance)) return false;
		Attendance arg1 = (Attendance) arg0;
		return this.hashCode() == arg1.hashCode();
	}
	@Override
	public int hashCode() {
		if (this.date == null){
			return -1;
		}
		return this.date.hashCode()* 1000000 + this.pid;
	}
	
	
}
