package com.geranium.gift.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.geranium.gift.model.enums.Occasion;
import com.geranium.gift.model.enums.Relationship;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Occasion occasion;

    private LocalDate eventDate;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    @Column(length = 1000)
    private String notes;

    private String imageUrl;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Gift> gifts = new ArrayList<>();

    public Person() {
    }

    public void addGift(Gift gift) {
        gifts.add(gift);
        gift.setPerson(this);
    }

    public void removeGift(Gift gift) {
        gifts.remove(gift);
        gift.setPerson(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public String getNotes() {
        return notes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occasion=" + occasion +
                ", eventDate=" + eventDate +
                ", relationship=" + relationship +
                ", notes='" + notes + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}