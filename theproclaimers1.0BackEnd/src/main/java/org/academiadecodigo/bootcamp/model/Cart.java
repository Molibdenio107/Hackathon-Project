package org.academiadecodigo.bootcamp.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cart")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Multiple JsonBackReference should distinguish them with a specific Reference name
     */
    @OneToOne
    @JsonManagedReference(value = "customerCart")
    private Customer customerCart;
    @ManyToOne
    @JsonBackReference(value = "productCart")
    private Product productCart;
    private Integer quantity;
    private Double price;

    @Transient
    private Long loadCustomer;

    @Transient
    private Long loadProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(Customer customer) {
        this.customerCart = customer;
    }

    public Product getProductCart() {
        return productCart;
    }

    public void setProductCart(Product product) {
        this.productCart = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getLoadCustomer() {
        return loadCustomer;
    }

    public void setLoadCustomer(Long loadCustomer) {
        this.loadCustomer = loadCustomer;
    }

    public Long getLoadProduct() {
        return loadProduct;
    }

    public void setLoadProduct(Long loadProduct) {
        this.loadProduct = loadProduct;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customerCart +
                ", product=" + productCart +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
