package com.cs465.groceryrun.enums;

import java.util.ArrayList;

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
    String timestamp;
    String title;
    String person;
    String role;
    ArrayList<GroceryListItem> groceryList;
    String date;
    String dueDate;
    int dueTime;
    String address;
    String note;
    String status;
    double rating;
    double groceryPrice = -1;
    double gratuity;

    public Transaction () {}

    public int getId () {return id;}

    public void setId(int id) {this.id = id;}

    public String getTimestamp () {return timestamp;}

    public void setTimestamp (String timestamp) {this.timestamp = timestamp;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public ArrayList<GroceryListItem> getGroceryList () {return groceryList;}

    public void setGroceryList (ArrayList<GroceryListItem> groceryList) {this.groceryList = groceryList;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    public int getDueTime () {return dueTime;}

    public void setDueTime (int dueTime) {this.dueTime = dueTime;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public double getRating () {return rating;}

    public void setRating (double rating) {this.rating = rating;}

    public double getGroceryPrice () {return groceryPrice;}

    public void setGroceryPrice (double groceryPrice) {this.groceryPrice = groceryPrice;}

    public double getGratuity() {return gratuity;}

    public void setGratuity(double amount) {this.gratuity = amount;}
}
