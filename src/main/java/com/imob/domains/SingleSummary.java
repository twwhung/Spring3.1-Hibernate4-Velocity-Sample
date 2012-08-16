package com.imob.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SingleSummary {
	
	private int pid;	
	private int winCount;
	private int loseCount;
	private double avgDiff;
		
	
	@Id		
	@Column(name="pid")
	public int getPid(){		
		return pid;
	}
	public void setPid(int pid){
		this.pid = pid;
	}
	
	@Column(name="wincount")
	public int getWinCount(){		
		return winCount;
	}
	public void setWinCount(int winCount){
		this.winCount = winCount;
	}
	@Column(name="losecount")
	public int getLoseCount(){		
		return loseCount;
	}
	public void setLoseCount(int loseCount){
		this.loseCount = loseCount;
	}
	@Column(name="avgdiff")
	public double getAvgDiff(){		
		return avgDiff;
	}
	public void setAvgDiff(double avgDiff){
		this.avgDiff = avgDiff;
	}
	
	
	
}