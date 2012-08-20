package com.imob.domains;
import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="players")
public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6552668997933529188L;
	private int id;
	private int gid;
	private String name;

	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId(){		
		return id;
	}
	public void setId(int i){
		id = i;
	}
	@Column(name="gid", updatable = false)
	public int getGid(){		
		return gid;
	}
	public void setGid(int g){
		gid = g;
	}
	@Column(name="name")
	public String getName(){		
		return name;
	}
	public void setName(String n){
		name = n;
	}		
	
	
}
