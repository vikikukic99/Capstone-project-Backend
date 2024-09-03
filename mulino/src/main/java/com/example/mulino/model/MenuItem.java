package com.example.mulino.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MenuItem {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name="price", nullable = false)
    private int price;

    @Column(name="rate", nullable = false)
    private double rate;

    @Column(name = "prep", nullable = false)
    private int prep;

    @Column(name = "cook", nullable = false)
    private int cook;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
}
