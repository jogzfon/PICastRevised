package com.example.picastrevised;

public class ArtData {

    private String title;
    private String artImage;
    private String artDescription;
    private int artPrice;
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
    public ArtData(String title, String artImage, String artDescription, int artPrice, String artAuthor, String authorImage) {
        this.title = title;
        this.artImage = artImage;
        this.artDescription = artDescription;
        this.author = artAuthor;
        this.artPrice = artPrice;
        this.authorImage = authorImage;
    }

    public String getArtDescription() {
        return artDescription;
    }

    public String getAuthor() {
        return author;
    }
    public int getArtPrice() {
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
