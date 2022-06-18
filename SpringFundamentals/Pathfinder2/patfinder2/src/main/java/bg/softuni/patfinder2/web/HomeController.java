package bg.softuni.patfinder2.web;

import bg.softuni.patfinder2.model.RouteEntity;
import bg.softuni.patfinder2.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private RouteService routeService;

    @Autowired
    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<RouteEntity> routes = routeService.getMostCommented();

        model.addAttribute("mostCommented", routes.get(0));

        return "index";
    }
}
