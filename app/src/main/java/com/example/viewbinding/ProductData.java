// ProductData.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.viewbinding;

public class ProductData {
    private String image;
    private double price;
    private Rating rating;
    private String description;
    private long id;
    private String title;
    private String category;

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public double getPrice() { return price; }
    public void setPrice(double value) { this.price = value; }

    public Rating getRating() { return rating; }
    public void setRating(Rating value) { this.rating = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public double getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getCategory() { return category; }
    public void setCategory(String value) { this.category = value; }



}

// Rating.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

class Rating {
    private double rate;
    private long count;

    public double getRate() { return rate; }
    public void setRate(double value) { this.rate = value; }

    public long getCount() { return count; }
    public void setCount(long value) { this.count = value; }
}
