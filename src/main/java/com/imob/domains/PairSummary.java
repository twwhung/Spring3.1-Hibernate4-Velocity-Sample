package com.imob.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class PairSummary implements Serializable{
	
	private int pid1;
	private int pid2;
	private int winCount;
	private int loseCount;
	private double avgDiff;
		
	
	
	
	@Id
	@Column(name="pid1")
	public int getPid1(){		
		return pid1;
	}
	public void setPid1(int pid1){
		this.pid1 = pid1;
	}
	@Id
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
	@Override
	public boolean equals(Object arg0) {
	if(arg0 == null) return false;
	if(!(arg0 instanceof PairSummary)) return false;
	PairSummary arg1 = (PairSummary) arg0;
	return (String.valueOf(this.pid1) + "pair" + String.valueOf(this.pid2)).equals(
			String.valueOf(arg1.getPid1()) + "pair" + String.valueOf(arg1.getPid2()));
	}
	@Override
	public int hashCode() {
	return (String.valueOf(this.pid1) + "pair" + String.valueOf(this.pid2)).hashCode();
	}
	
	
}
