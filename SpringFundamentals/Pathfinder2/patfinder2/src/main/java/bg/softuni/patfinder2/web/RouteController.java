package bg.softuni.patfinder2.web;

import bg.softuni.patfinder2.model.views.RouteDetailsView;
import bg.softuni.patfinder2.model.views.RouteIndexView;
import bg.softuni.patfinder2.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRouts(Model model){
        List<RouteIndexView> routes = this.routeService.getAllRoutes();
        model.addAttribute("routes", routes);

        return "routes";
    }

    @GetMapping("/details/{routeId}")
    public String getRoute(@PathVariable("routeId") Long routeId, Model model) {
        RouteDetailsView route = this.routeService.getRoute(routeId);

        model.addAttribute("route", route);
        return "route-details";
    }
}
