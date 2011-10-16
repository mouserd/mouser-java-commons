package com.pixelus.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pixelus.ModelEntity;

@Entity
@Table(name="Company")
public class Company 
	implements ModelEntity<Long>, Serializable {
	
	private static final long serialVersionUID = 3736121467799075340L;
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	public Company() {
	}
	
	public Company(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
