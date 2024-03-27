package com.hilmigndogdu.graduationproject;

public class Cards {

    private String imageName;
    private String productName;
    private String categoryName;
    private String brandName;

    public Cards(String imageName, String productName, String categoryName, String brandName) {
        this.imageName = imageName;
        this.productName = productName;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
