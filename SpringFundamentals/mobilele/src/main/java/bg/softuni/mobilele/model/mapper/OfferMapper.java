package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilele.model.dto.offer.OfferDetailDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDto addOfferDto);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    OfferDetailDTO offerEntityToCardListingOfferDto(OfferEntity offerEntity);

}
