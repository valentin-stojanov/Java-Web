package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.offer.AddOfferDto;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService,
                           BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allOffers(Model model,
                           @PageableDefault(sort = "price", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable pageable){
        model.addAttribute("offers", this.offerService.getAllOffers(pageable));
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model){
        if (!model.containsAttribute("addOfferModel")){
            model.addAttribute("addOfferModel", new AddOfferDto());
        }
        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails){

        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("addOfferModel", addOfferModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }


        offerService.addOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }

    @GetMapping("/search")
    public String searchOffer() {
        return "offer-search";
    }

    @GetMapping("/{id}/details")
    public String getOfferDTO(@PathVariable("id") UUID id){
        return "details";
    }

}
