/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.lang.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
* @author David Mouser
*/
public class MutableBigDecimal {

    private BigDecimal bigDecimal;

    public MutableBigDecimal(BigDecimal bigDecimal) {

        this.bigDecimal = bigDecimal;
    }

    public MutableBigDecimal() {

        bigDecimal = BigDecimal.ZERO;
    }

    public MutableBigDecimal(int i) {

        bigDecimal = new BigDecimal(i);
    }

    public MutableBigDecimal(double d) {

        bigDecimal = new BigDecimal(d);
    }

    public MutableBigDecimal(long l) {

        bigDecimal = new BigDecimal(l);
    }

    public int intValue() {

        return bigDecimal.intValue();
    }

    public double doubleValue() {

        return bigDecimal.doubleValue();
    }

    public Long longValue() {

        return bigDecimal.longValue();
    }

    public void add(BigDecimal augend) {

        bigDecimal = bigDecimal.add(augend);
    }

    public void subtract(BigDecimal augend) {

        bigDecimal = bigDecimal.subtract(augend);
    }

    public void divide(BigDecimal augend) {

        bigDecimal = bigDecimal.divide(augend);
    }

    public void multiply(BigDecimal augend) {

        bigDecimal = bigDecimal.multiply(augend);
    }

    public void divide(BigDecimal augend, int precision, RoundingMode roundingMode) {

        bigDecimal = bigDecimal.divide(augend, precision, roundingMode);
    }
}
