package com.cs465.groceryrun.groceryrun;

import java.util.GregorianCalendar;

/**
 * Created by Oleksiy Kamenyev on 11/27/2015.
 */
public class Transaction {
    String name;
    String person;
    boolean isShopper;
    GregorianCalendar date;
    GregorianCalendar dueDate;
    String status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public boolean getIsShopper() {return  isShopper;}

    public void setIsShopper (boolean isShopper) {this.isShopper = isShopper;}

    public GregorianCalendar getDate() {return date;}

    public void setDate(GregorianCalendar date) {this.date = date;}

    public GregorianCalendar getDueDate() {return dueDate;}

    public void setDueDate(GregorianCalendar dueDate) {this.dueDate = dueDate;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
