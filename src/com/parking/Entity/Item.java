package com.parking.Entity;

import java.io.Serializable;

/**
 * Created by siddhahastmohapatra on 18/12/16.
 */
public interface Item extends Comparable<Item>, Serializable {

    public String getKey();
    public String getColor();

}
