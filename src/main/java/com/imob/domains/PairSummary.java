package com.imob.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PairSummary {
	private String pair;
	private int pid1;
	private int pid2;
	private int winCount;
	private int loseCount;
	private double avgDiff;
		
	
	@Id
	@Column(name="pair")
	public String getPair(){
		return pair;
	}
	public void setPair(String pair){
		this.pair = pair;
	}
	
	@Column(name="pid1")
	public int getPid1(){		
		return pid1;
	}
	public void setPid1(int pid1){
		this.pid1 = pid1;
	}
	@Column(name="pid2")
	public int getPid2(){		
		return pid2;
	}
	public void setPid2(int pid2){
		this.pid2 = pid2;
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
