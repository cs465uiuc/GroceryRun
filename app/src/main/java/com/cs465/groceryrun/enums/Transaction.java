package com.cs465.groceryrun.enums;

import java.util.GregorianCalendar;

/**
 * Created by Oleksiy Kamenyev on 11/27/2015.
 */
public class Transaction {
    int id = -1;
    String name;
    String person;
    //boolean isShopper;
    String role;
    String date;
    String dueDate;
    //GregorianCalendar date;
    //GregorianCalendar dueDate;
    String status;
    double rating;
    double amount;

    public Transaction () {}

    public int getId () {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    //public boolean getIsShopper() {return  isShopper;}

    //public void setIsShopper (boolean isShopper) {this.isShopper = isShopper;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    //public void setDate(GregorianCalendar date) {this.date = date;}

    //public GregorianCalendar getDueDate() {return dueDate;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    //public void setDueDate(GregorianCalendar dueDate) {this.dueDate = dueDate;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public double getRating () {return rating;}

    public void setRating (double rating) {this.rating = rating;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}
}
