/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class User
      implements ModelEntity<Long>, Serializable {

    private static final long serialVersionUID = -4145030714084815206L;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public Long getId() {

        return id;
    }

    public void setId(final Long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(final String firstName) {

        this.firstName = firstName;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(final String surname) {

        this.surname = surname;
    }

    public Company getCompany() {

        return company;
    }

    public void setCompany(final Company company) {

        this.company = company;
    }
}
