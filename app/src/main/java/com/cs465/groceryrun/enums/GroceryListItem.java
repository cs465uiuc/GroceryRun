package com.cs465.groceryrun.enums;

/**
 * Created by tdw6193 on 12/2/2015.
 */
public class GroceryListItem {

    int id = -1;
    String associatedTransactionID = null;
    String item = null;
    int itemQuantity = 0;
    boolean itemBought = false;

    public GroceryListItem() {}

    public int getId () {return id;}

    public void setId (int id) {this.id = id;}

    public String getAssociatedTransactionID () {return associatedTransactionID;}

    public void setAssociatedTransactionID (String associatedTransactionID) {this.associatedTransactionID = associatedTransactionID;}

    public String getItem () {return item;}

    public void setItem (String item) {this.item = item;}

    public int getItemQuantity () {return itemQuantity;}

    public void setItemQuantity (int itemQuantity) {this.itemQuantity = itemQuantity;}

    public boolean getIsItemBought () {return itemBought;}

    public void setIsItemBought (boolean itemBought) {this.itemBought = itemBought;}
}
