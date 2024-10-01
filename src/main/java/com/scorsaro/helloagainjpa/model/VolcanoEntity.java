package com.scorsaro.helloagainjpa.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Table(name = "volcanoes")
public class VolcanoEntity extends MountainEntity {



    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private VolcanoType volcanoType;

    @Embedded
    private Position position;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "latitude_on_mars")),
            @AttributeOverride(name = "longitude", column = @Column(name = "longitude_on_mars"))
    })
    private Position positionOnMars;


    public VolcanoEntity(String name, Integer height, Boolean isActive) {
        super(name, height);
        this.isActive = isActive;
    }
}
