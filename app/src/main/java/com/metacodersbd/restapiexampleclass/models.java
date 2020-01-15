package com.metacodersbd.restapiexampleclass;

import com.google.gson.annotations.SerializedName;

public class models {

    @SerializedName("name")
    String name;
    @SerializedName("realname")
    String realname;



    public models(String name, String realname) {
        this.name = name;
        this.realname = realname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
