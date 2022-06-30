package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(){
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model){
        if (!model.containsAttribute("addOfferModel")){
            model.addAttribute("addOfferModel", new AddOfferDto());
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("addOfferModel", addOfferModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }

        //TODO

        return "redirect:/";
    }

}
