package com.example.owner.trainingtask.Details;



public class ReviewDetails {

    String Comment, UserName, Image;
    double Rate;

    public ReviewDetails(String comment, String userName, String image, double rate) {

        this.Comment = comment;
        this.UserName = userName;
        this.Image = image;
        this.Rate = rate;
    }


    public String getComment() {
        return Comment;
    }

    public String getUserName() {
        return UserName;
    }

    public String getImage() {
        return Image;
    }

    public double getRate() {
        return Rate;
    }

}
