package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.model.RouteEntity;
import bg.softuni.patfinder2.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<RouteEntity> getMostCommented() {

        return routeRepository.findMostCommented();
    }
}
