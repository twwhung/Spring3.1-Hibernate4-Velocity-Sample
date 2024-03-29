package com.imob.domains;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.validation.annotation.Validated;




@Validated
@Entity
@Table(name="games")
//@GameValid(message="Unvalid Game config")
@ScriptAssert(lang = "javascript", script = "_this.validGame()", message = "Not valid game")
public class Game {
	
	private int id;
	private int gid;
	private int type;
	private int winPid1;
	private int winPid2;
	private Date date;
	
	private int winScore;
	private int losePid1;
	private int losePid2;
	
	private int loseScore;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId(){		
		return id;
	}
	public void setId(int i){
		id = i;
	}
	@Min(1)
	@Column(name="gid", updatable = false)
	public int getGid(){		
		return gid;
	}
	public void setGid(int g){
		gid = g;
	}
	
	@Range(min=0,max=1,message="Unvalid Type")
	@Column(name="type", updatable = false)	
	public int getType(){		
		return type;
	}	
	public void setType(int type){
		this.type = type;
	}
	@Min(1)
	@Column(name="winpid1")
	public int getWinPid1(){		
		return winPid1;
	}
	public void setWinPid1(int winPid1){
		if(winPid1 > this.winPid2 && winPid2 != 0){
			this.winPid1 = this.winPid2;
			this.winPid2 = winPid1;
			return ;
		}
		this.winPid1 = winPid1;
	}
	@Min(1)
	@Column(name="winpid2")
	public int getWinPid2(){		
		return winPid2;
	}
	public void setWinPid2(int winPid2){
		if (winPid2 < winPid1){
			this.winPid2 = winPid1;
			winPid1 = winPid2;
			return ;
		}
		this.winPid2 = winPid2;
	}
	
	@Min(1)
	@Column(name="winscore")
	public int getWinScore(){		
		return winScore;
	}
	public void setWinScore(int winScore){
		this.winScore = winScore;
	}	
	@Min(1)
	@Column(name="losepid1")
	public int getLosePid1(){		
		return losePid1;
	}
	public void setLosePid1(int losePid1){
		if(losePid1 > this.losePid2 && losePid2 != 0){
			this.losePid1 = this.losePid2;
			this.losePid2 = losePid1;
			return ;
		}
		this.losePid1 =losePid1;
	}
	@Min(1)
	@Column(name="losepid2")
	public int getLosePid2(){		
		return losePid2;
	}
	public void setLosePid2(int losePid2){
		if (losePid2 < losePid1){
			this.losePid2 = losePid1;
			losePid1 = losePid2;
			return ;
		}			
		this.losePid2 = losePid2;
	}	
	@Min(0)
	@Column(name="losescore")
	public int getLoseScore(){		
		return loseScore;
	}
	public void setLoseScore(int loseScore){
		this.loseScore = loseScore;
	}	
	public boolean validGame(){
		if (winPid1 >= winPid2 || winPid1 == losePid1 || winPid1 == losePid2 || winPid2 == losePid1 || 
				winPid2 == losePid2 || losePid1 >= losePid2 || winScore <= loseScore){
			return false;			
		}		
		return true;
	}		
	
	@Column
	@NotNull
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}		
}
