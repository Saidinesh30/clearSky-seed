package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class WindDetails {
	
	@Id
	private String windId;
	private String speed;
	private String degree;
	
	public WindDetails() {
		this.windId = UUID.randomUUID().toString();
	}
		
	public String getId() {
		return windId;
	}
	public void setId(String id) {
		this.windId = id;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}


	@Override
	public String toString() {
		return "WindDetails [id=" + windId + ", speed=" + speed + ", degree=" + degree + "]";
	}
	
	
	
	

}
