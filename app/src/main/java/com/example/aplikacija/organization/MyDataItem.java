package com.example.aplikacija.organization;

/**
 * Created by Alen on 2.6.2016.
 */
public class MyDataItem {
    int id;
    String name;
    String desc;

    public MyDataItem(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MyDataItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
