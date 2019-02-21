package com.akira.learn.feign.vo;

import java.util.Date;

public class RemoteAPIParam {
    private String name;
    private int number;
    private Date date;
    private InnerObject innerObject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public InnerObject getInnerObject() {
        return innerObject;
    }

    public void setInnerObject(InnerObject innerObject) {
        this.innerObject = innerObject;
    }
}
