package com.secj3303.model;

import java.time.Year;

public class Person {
    private int id;
    private String name;
    private int yob;
    private double weight, height;

    public Person(){

    }

    public Person(int id, String name, int yob, double weight, double height){
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getYob() {
        return yob;
    }

    public int getAge() {
        int year = Year.now().getValue();
        return year - yob;
    }

    public double getBmi() {
    double bmi = weight / (height * height);
    return Math.round(bmi * 100.0) / 100.0;
    }

    public String getBmiCategory() {
    double bmi = getBmi();
    if (bmi < 18.5)
        return "Underweight";
    else if (bmi < 25)
        return "Normal weight";
    else if (bmi < 30)
        return "Overweight";
    else
        return "Obese";
}

 public void setId(int id) {
     this.id = id;
 }

 public void setHeight(double height) {
     this.height = height;
 }

 public void setName(String name) {
     this.name = name;
 }

 public void setWeight(double weight) {
     this.weight = weight;
 }

 public void setYob(int yob) {
     this.yob = yob;
 }

}

