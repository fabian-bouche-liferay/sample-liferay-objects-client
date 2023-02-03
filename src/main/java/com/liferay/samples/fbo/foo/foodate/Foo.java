package com.liferay.samples.fbo.foo.foodate;

import java.util.Date;

public class Foo extends LiferayObject {
    private Date date;
    private String title;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
