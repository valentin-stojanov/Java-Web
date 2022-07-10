package com.softuni.battleships.models;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private long health;
    private long power;

    private LocalDate created;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public ShipEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipEntity setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipEntity setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity owner) {
        this.user = owner;
        return this;
    }
}
