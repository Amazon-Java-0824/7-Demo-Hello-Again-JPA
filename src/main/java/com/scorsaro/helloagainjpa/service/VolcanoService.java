package com.scorsaro.helloagainjpa.service;

import com.scorsaro.helloagainjpa.model.VolcanoEntity;
import com.scorsaro.helloagainjpa.repository.VolcanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // this annotation create a constructor with all the final values
@Slf4j
public class VolcanoService {

    private final VolcanoRepository volcanoRepository;

    @Transactional // this makes the operation atomic
    public VolcanoEntity save(VolcanoEntity volcanoEntity) {
        log.info("Saving entity: {}", volcanoEntity);
        return volcanoRepository.save(volcanoEntity);
    }

    public List<VolcanoEntity> findAll() {
        log.info("Retrieving all volcanoes");
        return volcanoRepository.findAll();
    }

    public Optional<VolcanoEntity> findById(Long id){
        log.info("Looking for volcano with id = {}", id);
        return volcanoRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id){
        log.info("Looking to delete volcano with id = {}", id);
        volcanoRepository.deleteById(id);
    }

    @Transactional
    public Optional<VolcanoEntity> update(Long id, VolcanoEntity newVolcanoInfo) {
        log.info("Looking to update volcano with id = {}", id);
        var volcanoToUpdate = volcanoRepository.findById(id);
        if (volcanoToUpdate.isPresent()) {
            VolcanoEntity volcanoEntity = volcanoToUpdate.get();
            volcanoEntity.setName(newVolcanoInfo.getName());
            volcanoEntity.setHeight(newVolcanoInfo.getHeight());
            volcanoEntity.setIsActive(newVolcanoInfo.getIsActive());
            return Optional.of(volcanoRepository.save(volcanoEntity));
        }
        return Optional.empty();
    }



    public List<VolcanoEntity> findAllTheVolcanoesThatAreActive(){
        return volcanoRepository.findAllByIsActive(true);
    }

    public List<VolcanoEntity> findAllTheVolcanoesThatAreNotActive(){
        return volcanoRepository.findAllByIsActive(false);
    }

    public List<VolcanoEntity> findAllVolcanoesWithMinimumHeight(Integer minimumHeight){
        return volcanoRepository.findAllByHeightBefore(minimumHeight);
    }

    public List<VolcanoEntity> findAllActiveVolcanoesWithHeightBetween(Integer min, Integer max){
        return volcanoRepository.findAllByIsActiveTrueAndHeightBetween(min, max);
    }

    public List<VolcanoEntity> findAllByNameContainingIgnoreCaseOrderByNameDesc(String name) {
        return volcanoRepository.findAllByNameContainingIgnoreCaseOrderByNameDesc(name);
    }

    public List<VolcanoEntity> findByNameJPQL(String name) {
        return volcanoRepository.findAllVolcanoesThatHaveACertainNameThatImGonnaPassYou(name);
    }

    public List<VolcanoEntity> findByNameNative(String name) {
        return volcanoRepository.findAllVolcanoesThatHaveACertainNameThatImGonnaPassYouNative(name);
    }

    public Optional<VolcanoEntity> findFirstByIdAndIsActiveTrue(Long id) {
        return volcanoRepository.findFirstByIdAndIsActiveTrue(id);
    }




}
