package app.exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne(optional = false)
    private Category category;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderedItems;

    public Item() {
        this.orderedItems = new LinkedList<>();
    }

    public Item(String name, Category category, BigDecimal price, List<OrderItem> orderedItems) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.orderedItems = orderedItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
