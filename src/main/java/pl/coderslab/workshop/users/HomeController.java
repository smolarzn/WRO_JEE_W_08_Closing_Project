package pl.coderslab.workshop.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserServiceImpl userService;
    private final AircraftRepository aircraftRepository;
    private final AircraftService aircraftService;

    @GetMapping("/")
    public String home(Model model) {
//        Aircraft random = aircraftRepository.findRandom();
//        model.addAttribute("image", aircraftService.image(random));
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/logout")
    public String logoutForm() {
        return "user/logout";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid User user, BindingResult result, @RequestParam String repass, Model model) {
        if (!user.getPassword().equals(repass)) {
            model.addAttribute("passError", "");
            return "user/register";
        }
        if (result.hasErrors()) {
            return "user/register";
        }
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("emailError", "");
            return "user/register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

}
