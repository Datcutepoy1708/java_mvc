package com.example.demo.domain;





import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
@Table(name="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long quantity;
    private double price;

    //order_id
    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;

    @Override
    public String toString() {
        return "OrderDetail [id=" + id + ", quantity=" + quantity + ", price=" + price + ", order=" + order
                + ", prodect=" + prodect + "]";
    }

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product prodect;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProdect() {
        return prodect;
    }

    public void setProdect(Product prodect) {
        this.prodect = prodect;
    }
}
