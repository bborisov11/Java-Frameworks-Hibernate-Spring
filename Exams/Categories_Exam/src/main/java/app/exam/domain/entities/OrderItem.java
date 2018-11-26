package app.exam.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Item item;
    @Column(nullable = false)
    private Integer quantitiy;

    public OrderItem() {
    }

    public OrderItem(Item item, Integer quantitiy) {
        this.item = item;
        this.quantitiy = quantitiy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantitiy() {
        return this.order.getOrderedItems().size();
    }

    public void setQuantitiy(Integer quantitiy) {
        this.quantitiy = quantitiy;
    }
}
