package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilele.model.dto.offer.OfferDetailDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;

    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository,
                        OfferMapper offerMapper,
                        UserRepository userRepository,
                        ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable){
        return this.offerRepository
                .findAll(pageable)
                .map(this.offerMapper::offerEntityToCardListingOfferDto);
    }

    public void addOffer(AddOfferDto addOfferDto, UserDetails userDetails){
        OfferEntity newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDto);

                UserEntity seller = userRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow();

        ModelEntity model = modelRepository
                .findById(addOfferDto.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        this.offerRepository.save(newOffer);
    }

//    public List<OfferDetailDTO> findOfferByOfferName(String query){
//        return this.offerRepository
//
//    }

//    private OfferEntity mapToEntity(AddOfferDto addOfferDto){
//        //TODO
//    }
}
