package pl.coderslab.workshop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.model.Aircraft;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AircraftService aircraftService;
    private final AircraftRepository aircraftRepository;

    @GetMapping("/mainPage")
    public String mainPage(Model model) {
        List<Aircraft> lastAdded = aircraftRepository.lastAdded();
        Map<String, String> nameAndImage = new HashMap<>();
        for (Aircraft a : lastAdded) {
            nameAndImage.put(a.getName(), aircraftService.image(a));
        }
        model.addAttribute("lastAdded", nameAndImage);
        return "user/mainPage";
    }

    @GetMapping("/changeData")
    @ResponseBody
    public String changeData(@AuthenticationPrincipal UserDetails customUser){

        return "logged as " + customUser;
    }
}
