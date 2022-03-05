package _02_SalesDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id",
    referencedColumnName = "id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id",
    referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_location_id",
    referencedColumnName = "id")
    private StoreLocation storeLocation;

    @Column(nullable = false)
    private LocalDateTime date;

    public Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = LocalDateTime.now();
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
