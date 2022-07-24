package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.model.RouteEntity;
import bg.softuni.patfinder2.model.views.RouteIndexView;
import bg.softuni.patfinder2.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<RouteEntity> getMostCommented() {
        return routeRepository.findMostCommented();
    }

    public List<RouteIndexView> getAllRoutes(){
        return this.routeRepository
                .findAll()
                .stream()
                .map(route ->new RouteIndexView(
                        route.getId(),
                        route.getName(),
                        route.getDescription(),
                        route.getPictures()
                                .stream()
                                .findFirst()
                                .get()
                                .getUrl()
                )).collect(Collectors.toList());
    }
}
