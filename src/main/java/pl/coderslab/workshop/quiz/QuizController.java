package pl.coderslab.workshop.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.User;
import pl.coderslab.workshop.model.aircraftProperties.*;
import pl.coderslab.workshop.users.CurrentUser;
import pl.coderslab.workshop.users.UserRepository;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final AircraftRepository aircraftRepository;
    private final AircraftService aircraftService;
    private final QuizService quizService;
    private final UserRepository userRepository;


    @GetMapping("/quiz")
    public String quiz(Model model) {
        Aircraft aircraft = aircraftRepository.randomWithImage();
        model.addAttribute("id", aircraft.getId());
        model.addAttribute("role", quizService.getParametersToQuiz(aircraft.getAircraftRole(), Arrays.asList(AircraftRole.values())));
        model.addAttribute("assignment", Assignment.values());
        quizService.randomNames(aircraft, model);
        quizService.randomRange(aircraft, model);
        model.addAttribute("image", aircraftService.image(aircraft));
        model.addAttribute("aircraft", new Aircraft());
        return "quiz/quiz";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam Long id, Aircraft aircraft, @AuthenticationPrincipal CurrentUser currentUser, Model model) throws NullPointerException {
        User user = currentUser.getUser();
        Aircraft aircraftFromDB = aircraftRepository.findById(id).get();
        Map<String, String> wrongAnswers = new HashMap<>();
        if (!aircraftFromDB.equals(aircraft)) {
            if (!aircraftFromDB.getName().equals(aircraft.getName())) {
                wrongAnswers.put("Name", aircraftFromDB.getName());
            }
            if (aircraft.getAssignment() != null) {
                if (!aircraftFromDB.getAssignment().equals(aircraft.getAssignment())) {
                    wrongAnswers.put("Assignment", aircraftFromDB.getAssignment().toString());
                }
            }
            if (aircraft.getAircraftRole() != null) {
                if (!aircraftFromDB.getAircraftRole().equals(aircraft.getAircraftRole())) {
                    wrongAnswers.put("Aircraft role", aircraftFromDB.getAircraftRole().toString()
                    );
                }
            }

        }
        if (aircraft.getRangeOfAircraft() != null) {

            if (!aircraftFromDB.getRangeOfAircraft().equals(aircraft.getRangeOfAircraft())) {
                wrongAnswers.put("Range", aircraftFromDB.getRangeOfAircraft().toString());
            }
        }

        if (wrongAnswers.isEmpty()) {
//            quizService.addToFamiliarAircraftList(user, aircraftFromDB);
//            User user1 = currentUser.getUser();
//            model.addAttribute("familiar", quizService.familiarAircraftList(user1));


        } else {
            model.addAttribute("wrongAnswers", wrongAnswers);
//            quizService.removeFromFamiliarAircraftList(user, aircraftFromDB);
//            User user2 = currentUser.getUser();
//            model.addAttribute("familiar", quizService.familiarAircraftList(user2));
        }
//        model.addAttribute("aircraftId", aircraftFromDB.getId());

        return "quiz/results";
    }
}
