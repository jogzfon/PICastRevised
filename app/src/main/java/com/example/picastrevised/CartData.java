package com.example.picastrevised;

public class CartData {

    private String title;
    private String artImage;
    private double artPrice;
    private double artPriceUSD;
    private double artPriceSGD;
    private double artPriceJPY;
    private String author;
    private String user;
    private String description;

    public CartData(String title, String artImage, double artPrice, String author, String user,String description) {
        this.title = title;
        this.artImage = artImage;
        this.artPrice = artPrice;
        this.author = author;
        this.user = user;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtImage() {
        return artImage;
    }

    public void setArtImage(String artImage) {
        this.artImage = artImage;
    }

    public double getArtPrice() {
        return artPrice;
    }

    public void setArtPrice(double artPrice) {
        this.artPrice = artPrice;
    }

    public double getArtPriceUSD() {
        return artPrice * 0.017;
    }

    public void setArtPriceUSD(double artPriceUSD) {
        this.artPriceUSD = artPriceUSD;
    }

    public double getArtPriceSGD() {
        return artPrice * 0.024;
    }

    public void setArtPriceSGD(double artPriceSGD) {
        this.artPriceSGD = artPriceSGD;
    }

    public double getArtPriceJPY() {
        return artPrice * 2.48;
    }

    public void setArtPriceJPY(double artPriceJPY) {
        this.artPriceJPY = artPriceJPY;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
