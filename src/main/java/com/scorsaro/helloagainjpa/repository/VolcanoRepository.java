package com.scorsaro.helloagainjpa.repository;

import com.scorsaro.helloagainjpa.model.VolcanoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VolcanoRepository extends JpaRepository<VolcanoEntity, Long> {


    Optional<VolcanoEntity> findFirstByIdAndIsActiveTrue(Long id);

    List<VolcanoEntity> findAllByNameContainingIgnoreCaseOrderByNameDesc(String name);

    List<VolcanoEntity> findAllByIsActive(Boolean isActive);
    List<VolcanoEntity> findAllByHeightBefore(Integer minimumHeight);

    List<VolcanoEntity> findAllByIsActiveTrueAndHeightBetween(Integer min, Integer max);

    @Query("SELECT v from VolcanoEntity v where v.name = :volcanoName") //JPQL
    List<VolcanoEntity> findAllVolcanoesThatHaveACertainNameThatImGonnaPassYou(@Param("volcanoName") String name);


    @Query(value = "SELECT * from volcanoes where name = :volcanoName", nativeQuery = true) // SQL
    List<VolcanoEntity> findAllVolcanoesThatHaveACertainNameThatImGonnaPassYouNative(@Param("volcanoName") String name);


}
