package org.academiadecodigo.bootcamp.persistence.model;

import javax.persistence.*;
@Entity
@Table(name = "Products")
public class Product extends AbstractModel {

    private double price;
    private String description;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
