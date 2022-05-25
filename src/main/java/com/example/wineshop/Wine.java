package com.example.wineshop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Wine {

    private @Id @GeneratedValue Long id;
    private String wine;
    private String year;
    private int num_reviews;
    private String country;
    private double price;
    private String body;
    private String acidity;

    Wine(){}

    public Wine(Long id, String wine, String year, int num_reviews, String country, double price, String body, String acidity) {
        this.id = id;
        this.wine = wine;
        this.year = year;
        this.num_reviews = num_reviews;
        this.country = country;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
    }


    public Long getId() {
        return id;
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

    public double getPrice() {
        return price;
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


    public void setPrice(double price) {
        this.price = price;
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
        return num_reviews == wine1.num_reviews && Double.compare(wine1.price, price) == 0 && Objects.equals(id, wine1.id) && Objects.equals(wine, wine1.wine) && Objects.equals(year, wine1.year) && Objects.equals(country, wine1.country) && Objects.equals(body, wine1.body) && Objects.equals(acidity, wine1.acidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wine, year, num_reviews, country, price, body, acidity);
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                 ", wine='" + wine + '\'' +
                ", year='" + year + '\'' +
                ", num_reviews=" + num_reviews +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", body='" + body + '\'' +
                ", acidity='" + acidity + '\'' +
                '}';
    }


}
