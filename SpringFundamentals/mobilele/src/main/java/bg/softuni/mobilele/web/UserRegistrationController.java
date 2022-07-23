package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.user.UserRegisterDto;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;
    private final LocaleResolver localeResolver;

    public UserRegistrationController(UserService userService,
                                      LocaleResolver localeResolver) {
        this.userService = userService;
        this.localeResolver = localeResolver;
    }
    @ModelAttribute("userModel")
    public UserRegisterDto initUserModel() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("userModel", userModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }
        userService.registerAndLogin(userModel, this.localeResolver.resolveLocale(request));

        return "redirect:/";
    }
}
