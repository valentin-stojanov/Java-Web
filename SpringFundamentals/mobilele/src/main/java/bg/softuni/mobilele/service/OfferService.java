package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public void addOffer(AddOfferDto addOfferDto){
        OfferEntity newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDto);
        // TODO - current user should be logged in.
//        UserEntity seller = userRepository
//                .findByEmail(currentUser.getEmail())
//                .orElseThrow();

        ModelEntity model = modelRepository
                .findById(addOfferDto.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
//        newOffer.setSeller(seller);

        this.offerRepository.save(newOffer);
    }

//    private OfferEntity mapToEntity(AddOfferDto addOfferDto){
//        //TODO
//    }
}
