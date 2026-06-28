package com.geranium.gift.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geranium.gift.model.enums.GiftStatus;
import jakarta.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idea;

    private Double price;

    private String link;

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

    public Double getPrice() {
        return price;
    }

    public String getLink() {
        return link;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
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
                ", link='" + link + '\'' +
                ", status=" + status +
                '}';
    }
}