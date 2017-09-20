package com.java.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class Region {
	
	
	 @Id
	// @GeneratedValue(strategy = GenerationType.AUTO) 
	 @Column(name="r_id")
	 private int r_id;
	 @Column(name="r_name")
	 private String r_name;
	 
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	 
	 
	 
	 
	 
	 

}
