package bg.softuni.mobilele.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

}
