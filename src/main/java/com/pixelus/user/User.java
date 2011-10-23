package com.pixelus.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pixelus.ModelEntity;
import com.pixelus.company.Company;

@Entity
@Table(name="USER_DETAILS")
public class User
	implements ModelEntity<Long>, Serializable {

	private static final long serialVersionUID = -4145030714084815206L;

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name="SURNAME", nullable = false)
	private String surname;
	
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private Company company;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurname() {
		
		return surname;
	}

	public void setSurname(String surname) {
		
		this.surname = surname;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
