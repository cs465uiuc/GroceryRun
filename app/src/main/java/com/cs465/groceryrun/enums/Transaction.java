package com.cs465.groceryrun.enums;

/**
 * Created by Oleksiy Kamenyev on 11/27/2015.
 */
public class Transaction {

    public static final int PROGRESS_REQUEST_RECEIVED = 5;
    public static final int PROGRESS_SHOPPING = 35;
    public static final int PROGRESS_DELIVERING = 65;
    public static final int PROGRESS_DELIVERED = 95;
    public static final int PROGRESS_CONFIRMED = 100;

    int id = -1;
    String name;
    String person;
    String role;
    String date;
    String dueDate;
    String address;
    String note;
    String status;
    double rating;
    double amount;

    public Transaction () {}

    public int getId () {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {return name;}

    public void setTitle(String name) {this.name = name;}

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public double getRating () {return rating;}

    public void setRating (double rating) {this.rating = rating;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}
}
