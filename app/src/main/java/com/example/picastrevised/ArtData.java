package com.example.picastrevised;

public class ArtData {

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
    public ArtData(String title, String artImage,String authorImage, String author) {
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
