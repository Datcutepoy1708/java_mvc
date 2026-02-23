package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalpPrice;
    public long getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Order [id=" + id + ", totalpPrice=" + totalpPrice + "]";
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getTotalpPrice() {
        return totalpPrice;
    }
    public void setTotalpPrice(double totalpPrice) {
        this.totalpPrice = totalpPrice;
    }

    
}
