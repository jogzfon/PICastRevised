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

    public CartData(String title, String artImage, double artPrice, String author, String user) {
        this.title = title;
        this.artImage = artImage;
        this.artPrice = artPrice;
        this.author = author;
        this.user = user;
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
        return artPriceUSD;
    }

    public void setArtPriceUSD(double artPriceUSD) {
        this.artPriceUSD = artPriceUSD;
    }

    public double getArtPriceSGD() {
        return artPriceSGD;
    }

    public void setArtPriceSGD(double artPriceSGD) {
        this.artPriceSGD = artPriceSGD;
    }

    public double getArtPriceJPY() {
        return artPriceJPY;
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
