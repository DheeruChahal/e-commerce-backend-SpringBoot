package com.Project.SpringBootProject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Component
@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private  String description;
    private String brand;
    private BigDecimal price;
    private String category;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") to change the format of date. BUt it is taken care of in the UI.
    private Date releaseDate;
    private Boolean ProductAvailable;
    private int stockQuantity;

    //now to store images;
    private String imageName;
    private String imageType;
    @Lob //image is saved in database as a large object, hence we have to use this annotation
    private byte[] imageDate;

    public Product(int id, String name, String description, String brand, BigDecimal price, String category, Date releaseDate, Boolean productAvailable, int stockQuantity, String imageName, String imageType, byte[] imageDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        ProductAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageDate = imageDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getProductAvailable() {
        return ProductAvailable;
    }

    public void setProductAvailable(Boolean productAvailable) {
        ProductAvailable = productAvailable;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageDate() {
        return imageDate;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", releaseDate=" + releaseDate +
                ", ProductAvailable=" + ProductAvailable +
                ", stockQuantity=" + stockQuantity +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageDate=" + Arrays.toString(imageDate) +
                '}';
    }
}
