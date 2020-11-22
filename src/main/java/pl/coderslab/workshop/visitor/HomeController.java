package pl.coderslab.workshop.visitor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.email.EmailSenderService;
import pl.coderslab.workshop.users.User;
import pl.coderslab.workshop.users.UserServiceImpl;
import pl.coderslab.workshop.varificationToken.VerificationTokenRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserServiceImpl userService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final EmailSenderService senderService;

    @GetMapping("/")
    public String home() {
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
    public String handleRegister(@Valid User user, BindingResult result, @RequestParam String repass, Model model, HttpServletRequest request) throws MessagingException {
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
        String appUrl = request.getContextPath();
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));

        return "redirect:/login";


    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration
            (@RequestParam("token") String token, @RequestParam("id") String id) throws MessagingException {
        String s = userService.validateVerificationToken(token);
        log.info("verification: {} ", s);
        User user = userService.findById(Long.parseLong(id));
        senderService.sendEmail(user.getEmail(), "Welcome!", user.getFirstName(), "/email/welcome.html");
        return "redirect:/login";
    }


}
