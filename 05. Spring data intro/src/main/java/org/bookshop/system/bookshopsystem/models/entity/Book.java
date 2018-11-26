package org.bookshop.system.bookshopsystem.models.entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    private Long bookId;
    private Integer ageRestriction;
    private Integer copies;
    private String description;
    private Integer editionType;
    private BigDecimal price;
    private LocalDate release_date;
    private String title;
    private Author author;
    private Set<Category> categories;

    public Book() {
        this.categories = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
    @Column(nullable = false)
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    @Column(name = "description",length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false)
    public Integer getEditionType() {
        return editionType;
    }

    public void setEditionType(Integer editionType) {
        this.editionType = editionType;
    }
    @Column(scale = 2, precision = 19,nullable = false)
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    @Column(name = "title",length = 50,nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(optional = false)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
   // @ManyToMany
   // @JoinTable(name = "book_category",
   // joinColumns =
    //@JoinColumn(name = "book_id",referencedColumnName = "Id"),
   // inverseJoinColumns =
   // @JoinColumn(name = "category_id",referencedColumnName = "Id"))
    @ManyToMany
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
