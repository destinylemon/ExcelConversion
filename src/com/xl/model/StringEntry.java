package com.xl.model;

public class StringEntry {
    String name;
    String value;
    int arrayType = -1; // 0: array  1:string-array  2:integer-array  3:integer 4:string

    public StringEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public StringEntry(String name, String value, int arrayType) {
        this.name = name;
        this.value = value;
        this.arrayType = arrayType;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int getArrayType() {
        return this.arrayType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setArrayType(int arrayType) {
        this.arrayType = arrayType;
    }
}
