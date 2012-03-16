/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.repository;

import com.pixelus.entity.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> findAll();
}
