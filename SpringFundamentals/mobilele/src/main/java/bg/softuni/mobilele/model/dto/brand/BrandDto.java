package bg.softuni.mobilele.model.dto.brand;

import bg.softuni.mobilele.model.dto.model.ModelDto;

import java.util.ArrayList;
import java.util.List;

public class BrandDto {
    private List<ModelDto> models;

    private String name;

    public List<ModelDto> getModels() {
        return models;
    }

    public BrandDto setModels(List<ModelDto> models) {
        this.models = models;
        return this;
    }

    public BrandDto setModels(ModelDto model) {
        if (this.models == null){
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }
}
