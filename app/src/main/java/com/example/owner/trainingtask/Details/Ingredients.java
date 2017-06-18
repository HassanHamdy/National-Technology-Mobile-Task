package com.example.owner.trainingtask.Details;


public class Ingredients {

    private String Name, DisplayQuantity, Unit;

    public Ingredients(String name, String displayQuantity, String unit) {
        this.Name = name;
        this.DisplayQuantity = displayQuantity;
        this.Unit = unit;
    }

    public String getName() {
        return Name;
    }

    public String getDisplayQuantity() {
        return DisplayQuantity;
    }

    public String getUnit() {
        return Unit;
    }

}
