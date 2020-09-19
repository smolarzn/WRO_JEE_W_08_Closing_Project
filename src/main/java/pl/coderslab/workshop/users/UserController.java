package pl.coderslab.workshop.users;

import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final AircraftService aircraftService;
    private final AircraftRepository aircraftRepository;

    @GetMapping("/mainPage")
    public String mainPage(Model model) {
        List<Aircraft> lastAdded = aircraftRepository.lastAdded();
        Map<String, String> nameAndImage = new HashMap<>();
        for (Aircraft a : lastAdded) {
            nameAndImage.put(a.getName(), aircraftService.image(a.getFile()));
        }
        model.addAttribute("lastAdded", nameAndImage);
        return "user/mainPage";
    }
//
//    @GetMapping("/quiz")
//    public String quiz(Model model) {
//        Aircraft aircraft = aircraftRepository.findRandom();
//        model.addAttribute("aircraft", aircraft.getName());
////        byte[] file = aircraft.getFile();
////        String image = "";
////        if (file != null && file.length > 0) {
////            image = Base64.getMimeEncoder().encodeToString(file);
////        }
//        model.addAttribute("image", userService.image(aircraft.getFile()));
//        return "quiz/quiz";
//    }


//    private final AircraftRepository aircraftRepository;

//    @ModelAttribute(name = "roles")
//    public List<AircraftRole> roles() {
//        return Arrays.asList(AircraftRole.values());
//    }
//
//    @GetMapping("/homepage")
//    public String quizList() {
//        return "homepage";
//    }
//
//    @GetMapping("/quiz")
//    public String quiz() {
//        return "quiz";
//    }
//
//    @GetMapping("/aircraftGroups")
//    public String aircraftRoles() {
//        return "choose";
//    }
//
//    @GetMapping("/list")
//    public String aircraftList(@RequestParam AircraftRole role, Model model) {
//        model.addAttribute("aircraft", aircraftRepository.findAllByAircraftRole(role));
//        return "listOfAircraft";
//    }

}
