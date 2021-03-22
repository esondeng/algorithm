package com.test.hession;

import java.io.Serializable;

/**
 * @author dengxiaolin
 * @since 2020/11/02
 */
public class To implements Serializable {

    private int id;

    private String value;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }
}
