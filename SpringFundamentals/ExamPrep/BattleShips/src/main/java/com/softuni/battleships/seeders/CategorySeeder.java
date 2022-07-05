package com.softuni.battleships.seeders;

import com.softuni.battleships.models.CategoryEntity;
import com.softuni.battleships.models.enums.ShipTypeEnum;
import com.softuni.battleships.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0){
            List<CategoryEntity> categories = Arrays.stream(ShipTypeEnum.values())
                    .map(CategoryEntity::new)
                    .collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);
        }

    }
}
