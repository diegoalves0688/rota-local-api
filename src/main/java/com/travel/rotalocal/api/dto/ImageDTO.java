package com.travel.rotalocal.api.dto;

public class ImageDTO {

    private String imageName;

    private String imageUrl;

    public ImageDTO(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return "http://localhost:8080/images/" + imageName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
