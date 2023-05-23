package com.example.picastrevised;

import java.io.Serializable;

public class ArtData implements Serializable {

    private String title;
    private String artImage;
    private String artDescription;
    private double artPrice;
    private double artPriceUSD;
    private double artPriceSGD;
    private double artPriceJPY;
    private String author;
    private String authorImage;



    public ArtData(String title, String artImage) {
        this.title = title;
        this.artImage = artImage;
    }
    public ArtData(String title, String artImage, String author) {
        this.title = title;
        this.artImage = artImage;
        this.author = author;
    }

    public ArtData(String title, String artImage, String authorImage, String author) {
        this.title = title;
        this.artImage = artImage;
        this.author = author;
        this.authorImage = authorImage;
    }
    public ArtData(String title, String artImage, String artDescription, double artPrice, String artAuthor, String authorImage) {
        this.title = title;
        this.artImage = artImage;
        this.artDescription = artDescription;
        this.author = artAuthor;
        this.artPrice = artPrice;
        this.artPriceUSD = artPrice * 0.018;
        this.artPriceSGD = artPrice * 0.024;
        this.artPriceJPY = artPrice * 2.48;
        this.authorImage = authorImage;
    }

    public ArtData(String title, String artImage, String authorImage, String artAuthor, double price) {
        this.title = title;
        this.artImage = artImage;
        this.authorImage = authorImage;
        this.author = artAuthor;
        this.artPrice = price;
    }

    public double getArtPriceUSD() {
        return artPrice * 0.018;
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
    public void setArtPrice(double artPrice) {
        this.artPrice = artPrice;
    }


    public String getArtDescription() {
        return artDescription;
    }

    public String getAuthor() {
        return author;
    }
    public double getArtPrice() {
        return artPrice;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public String getTitle() {
        return title;
    }

    public String getArtImage() {
        return artImage;
    }
}
