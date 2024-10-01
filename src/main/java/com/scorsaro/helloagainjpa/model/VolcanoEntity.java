package com.scorsaro.helloagainjpa.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "volcanoes")
public class VolcanoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer height;
    private Boolean isActive;


    public VolcanoEntity(String name, Integer height, Boolean isActive) {
        this.name = name;
        this.height = height;
        this.isActive = isActive;
    }
}
