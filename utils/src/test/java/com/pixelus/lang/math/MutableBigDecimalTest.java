/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.lang.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/*
* @author David Mouser
*/
public class MutableBigDecimalTest {

    @Test
    public void testDefaultConstructor() {

        assertThat(new MutableBigDecimal().intValue(), is(0));
    }

    @Test
    public void testBigDecimalConstructor() {

        assertThat(new MutableBigDecimal(new BigDecimal(10)).intValue(), is(10));
    }

    @Test
    public void testIntConstructor() {

        assertThat(new MutableBigDecimal(1).intValue(), is(1));
    }

    @Test
    public void testDoubleConstructor() {

        assertThat(new MutableBigDecimal(1.0).doubleValue(), is(1.0));
    }

    @Test
    public void testLongConstructor() {

        assertThat(new MutableBigDecimal(1L).longValue(), is(1L));
    }

    @Test
    public void testAdd() {

        MutableBigDecimal bigDecimal = new MutableBigDecimal(10);
        bigDecimal.add(new BigDecimal(10));

        assertThat(bigDecimal.intValue(), is(20));
    }

    @Test
    public void testSubtract() {

        MutableBigDecimal bigDecimal = new MutableBigDecimal(20);
        bigDecimal.subtract(new BigDecimal(10));

        assertThat(bigDecimal.intValue(), is(10));
    }

    @Test
    public void testDivide() {

        MutableBigDecimal bigDecimal = new MutableBigDecimal(20);
        bigDecimal.divide(new BigDecimal(4));

        assertThat(bigDecimal.intValue(), is(5));

        bigDecimal.divide(new BigDecimal(2));
        assertThat(bigDecimal.doubleValue(), is(2.5));
    }

    @Test
    public void testDivideWithRounding() {

        MutableBigDecimal bigDecimal = new MutableBigDecimal(10);
        bigDecimal.divide(new BigDecimal(3), 2, RoundingMode.HALF_UP);

        assertThat(bigDecimal.doubleValue(), is(3.33));
    }

    @Test
    public void testMultiply() {

        MutableBigDecimal bigDecimal = new MutableBigDecimal(10);
        bigDecimal.multiply(new BigDecimal(10));

        assertThat(bigDecimal.intValue(), is(100));
    }
}
