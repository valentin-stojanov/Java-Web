package com.softuni.battleships.repositories;

import com.softuni.battleships.models.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findByName(String name);

    List<ShipEntity> findByUserId(Long ownerId);
    List<ShipEntity> findByUserIdNot(Long ownerId);

    List<ShipEntity> findByOrderByHealthAscNameDescPowerAsc();
}
