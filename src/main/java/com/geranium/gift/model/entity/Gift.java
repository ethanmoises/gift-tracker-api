package com.geranium.gift.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geranium.gift.model.enums.GiftStatus;
import com.geranium.gift.model.enums.Priority;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idea;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal budget;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(length = 1000)
    private String notes;

    private String link;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private GiftStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    public Gift() {
    }

    public Long getId() {
        return id;
    }

    public String getIdea() {
        return idea;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getNotes() {
        return notes;
    }

    public String getLink() {
        return link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public GiftStatus getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStatus(GiftStatus status) {
        this.status = status;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", idea='" + idea + '\'' +
                ", price=" + price +
                ", budget=" + budget +
                ", quantity=" + quantity +
                ", priority=" + priority +
                ", notes='" + notes + '\'' +
                ", link='" + link + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}