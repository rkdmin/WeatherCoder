package com.example.weatherCoder.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClothesCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}