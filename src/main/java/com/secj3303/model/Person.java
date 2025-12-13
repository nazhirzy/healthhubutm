package com.secj3303.model;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int yob;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double height;

    public Person(){}

    public Person(int id, String name, String password, int yob, double weight, double height){
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
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

 public void setPassword(String password) {
     this.password = password;
 }

 public void setWeight(double weight) {
     this.weight = weight;
 }

 public void setYob(int yob) {
     this.yob = yob;
 }

}

