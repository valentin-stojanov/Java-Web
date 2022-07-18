package bg.softuni.patfinder2.web;

import bg.softuni.patfinder2.model.UserEntity;
import bg.softuni.patfinder2.model.dto.UserRegistrationDTO;
import bg.softuni.patfinder2.model.views.UserProfileView;
import bg.softuni.patfinder2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Struct;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

//    @ModelAttribute("userRegistrationDTO")
//    public void initForm(Model model) {
//        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
//    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegistration(@Valid UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/users/register";
        }

        this.authService.register(userRegistrationDTO);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        String username = principal.getName();
        UserEntity user = this.authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getEmail(),
                user.getFullName(),
                user.getAge(),
                user.getLevel() != null ? user.getLevel().name():"BEGINNER"
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
