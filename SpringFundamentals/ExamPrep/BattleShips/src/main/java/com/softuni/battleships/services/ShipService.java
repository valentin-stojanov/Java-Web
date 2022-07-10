package com.softuni.battleships.services;

import com.softuni.battleships.models.CategoryEntity;
import com.softuni.battleships.models.ShipEntity;
import com.softuni.battleships.models.UserEntity;
import com.softuni.battleships.models.dtos.CreateShipDTO;
import com.softuni.battleships.models.dtos.ShipDTO;
import com.softuni.battleships.models.enums.ShipTypeEnum;
import com.softuni.battleships.repositories.CategoryRepository;
import com.softuni.battleships.repositories.ShipRepository;
import com.softuni.battleships.repositories.UserRepository;
import com.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository, LoggedUser user, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = user;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<ShipEntity> byName = this.shipRepository.findByName(createShipDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        ShipTypeEnum type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipTypeEnum.BATTLE;
            case 1 -> ShipTypeEnum.CARGO;
            case 2 -> ShipTypeEnum.PATROL;
            default -> ShipTypeEnum.BATTLE;
        };

        CategoryEntity category = this.categoryRepository.findByName(type);
        Optional<UserEntity> user = this.userRepository.findById(this.loggedUser.getId());

        ShipEntity ship = new ShipEntity();
        ship.setName(createShipDTO.getName())
                .setPower(createShipDTO.getPower())
                .setHealth(createShipDTO.getHealth())
                .setCategory(category)
                .setUser(user.get())
                .setCreated(createShipDTO.getCreated());

        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getShipsNotOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc()
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }
}
