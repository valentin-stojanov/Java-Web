package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.model.dto.ModelDto;
import bg.softuni.mobilele.model.entity.BrandEntity;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDto> getAllBrands(){
        return this.brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());
    }

    private BrandDto mapBrand(BrandEntity brandEntity) {
        List<ModelDto> models = brandEntity
                .getModels()
                .stream()
                .map(this::mapModel)
                .toList();

        return new BrandDto()
                .setModels(models)
                .setName(brandEntity.getName());
    }

    private ModelDto mapModel(ModelEntity modelEntity){
        return new ModelDto()
                .setId(modelEntity.getId())
                .setName(modelEntity.getName());
    }
}
