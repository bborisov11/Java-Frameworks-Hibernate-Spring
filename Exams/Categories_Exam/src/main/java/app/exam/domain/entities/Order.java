package app.exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String customer;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private OrderType orderType;
    @Transient
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @ManyToOne(optional = false)
    private Employee employee;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderedItems;

    public Order() {
        this.orderType = OrderType.ForHere;
        this.orderedItems = new LinkedList<>();
    }

    public Order(String customer, Date date, OrderType orderType, BigDecimal totalPrice, Employee employee, List<OrderItem> orderedItems) {
        this.customer = customer;
        this.date = date;
        this.orderType = orderType;
        this.totalPrice = totalPrice;
        this.employee = employee;
        this.orderedItems = orderedItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderedItems) {
            totalPrice = totalPrice.add(orderItem.getItem().getPrice());
        }
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
