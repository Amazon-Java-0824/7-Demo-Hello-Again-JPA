package com.scorsaro.helloagainjpa.repository;

import com.scorsaro.helloagainjpa.model.MountainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainRepository extends JpaRepository<MountainEntity, Long> {
}
