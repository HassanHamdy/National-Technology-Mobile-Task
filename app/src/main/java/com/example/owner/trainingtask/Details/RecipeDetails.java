package com.example.owner.trainingtask.Details;

import java.util.ArrayList;



public class RecipeDetails {

    private String Title, Description, Category, SubCategory, Image, Instructions;
    private double Rate;
    private int ID;
    private ArrayList<Ingredients> ingredient;

    public RecipeDetails(int id, String title, String description, String category, String subCategory,
                         String image, String instructions, double rate, ArrayList<Ingredients> ingredient1) {
        this.ID = id;
        this.Title = title;
        this.Description = description;
        this.Category = category;
        this.SubCategory = subCategory;
        this.Image = image;
        this.Instructions = instructions;
        this.Rate = rate;
        this.ingredient = ingredient1;
    }


    public RecipeDetails(int id, String title, String category, String image, double rate) {
        this.ID = id;
        this.Title = title;
        this.Category = category;
        this.Image = image;
        this.Rate = rate;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public String getImage() {
        return Image;
    }

    public String getInstructions() {
        return Instructions;
    }

    public double getRate() {
        return Rate;
    }

    public ArrayList<Ingredients> getIngredient() {
        return ingredient;
    }

    public int getID() {
        return ID;
    }

}
