/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please contact
 * David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.entity;

/*
* @author David Mouser
*/
public class StubEntity
      implements ModelEntity<Long> {

    @Override
    public Long getId() {

        return 1L;
    }

    @Override
    public void setId(Long id) {

    }
}
