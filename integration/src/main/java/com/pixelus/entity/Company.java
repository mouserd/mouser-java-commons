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
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company
      implements ModelEntity<Long>, Serializable {

    private static final long serialVersionUID = 3736121467799075340L;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    public Company() {

    }

    public Company(final Long id) {

        this.id = id;
    }

    @Override
    public Long getId() {

        return id;
    }

    @Override
    public void setId(final Long id) {

        this.id = id;
    }
}
