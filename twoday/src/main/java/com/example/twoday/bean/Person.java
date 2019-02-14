package com.example.twoday.bean;

import android.util.Log;

public class Person {
    private String name="是高升啊";
    private String gender;
    private int age;

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public Person() {

    }

    private Person(String name) {
        this.name = name;
    }

    public void getInfo(String name) {
        this.name = name;
    }
    public void getInfo(String name,String gender,int age){
        Log.e("name和gender", name + ",gender=" + gender+",age="+age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
