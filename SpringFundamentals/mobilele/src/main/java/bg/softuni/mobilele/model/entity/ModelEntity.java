package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.entity.enums.CategoryEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private String imageUrl;

    private int startYear;

    private Long endYear;// Int?

    private LocalDateTime created;

    private LocalDateTime modified;

    private BrandEntity brand;
}
