package com.softuni.battleships.repositories;

import com.softuni.battleships.models.CategoryEntity;
import com.softuni.battleships.models.enums.ShipTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(ShipTypeEnum type);
}
