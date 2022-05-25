package com.example.wineshop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Wine {

    private @Id @GeneratedValue Long id;
    private String winery;
    private String wine;
    private String year;
    private int num_reviews;
    private String country;
    private String region;
    private double price;
    private String type;
    private String body;
    private String acidity;

    Wine(){}

    public Wine(Long id, String winery, String wine, String year, int num_reviews, String country, String region, double price, String type, String body, String acidity) {
        this.id = id;
        this.winery = winery;
        this.wine = wine;
        this.year = year;
        this.num_reviews = num_reviews;
        this.country = country;
        this.region = region;
        this.price = price;
        this.type = type;
        this.body = body;
        this.acidity = acidity;
    }


    public Long getId() {
        return id;
    }

    public String getWinery() {
        return winery;
    }

    public String getWine() {
        return wine;
    }

    public String getYear() {
        return year;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    //REVISAR
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine1 = (Wine) o;
        return num_reviews == wine1.num_reviews && Double.compare(wine1.price, price) == 0 && Objects.equals(id, wine1.id) && Objects.equals(winery, wine1.winery) && Objects.equals(wine, wine1.wine) && Objects.equals(year, wine1.year) && Objects.equals(country, wine1.country) && Objects.equals(region, wine1.region) && Objects.equals(type, wine1.type) && Objects.equals(body, wine1.body) && Objects.equals(acidity, wine1.acidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winery, wine, year, num_reviews, country, region, price, type, body, acidity);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", winery='" + winery + '\'' +
                ", wine='" + wine + '\'' +
                ", year='" + year + '\'' +
                ", num_reviews=" + num_reviews +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", body='" + body + '\'' +
                ", acidity='" + acidity + '\'' +
                '}';
    }


}
