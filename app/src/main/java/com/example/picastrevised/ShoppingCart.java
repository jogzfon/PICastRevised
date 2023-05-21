package com.example.picastrevised;

public class ShoppingCart {
    private String title;
    private String artImage;
    private String artDescription;
    private double artPrice;
    private double artPriceUSD;
    private double artPriceSGD;
    private double artPriceJPY;

    public ShoppingCart(String title, String artImage, double artPrice) {
        this.title = title;
        this.artImage = artImage;
        this.artPrice = artPrice;
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

    public String getArtDescription() {
        return artDescription;
    }

    public void setArtDescription(String artDescription) {
        this.artDescription = artDescription;
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
}
