package com.example.aplikasisqlite.model;

public class Data {
    int id;
    String name, address;

    public Data() {
    }

    public Data (int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Data (String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id)   {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
