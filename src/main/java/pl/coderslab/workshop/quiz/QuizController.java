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
        if (aircraft.getAircraftRole() != null) {
            model.addAttribute("role", quizService.getParametersToQuiz(aircraft.getAircraftRole(), Arrays.asList(AircraftRole.values())));
        }
        if (aircraft.getEnginesType() != null) {
            model.addAttribute("enginesType", quizService.getParametersToQuiz(aircraft.getEnginesType(), Arrays.asList(EnginesType.values())));
        }
        if (aircraft.getTail() != null) {
            model.addAttribute("tail", quizService.getParametersToQuiz(aircraft.getTail(), Arrays.asList(Tail.values())));
        }
        if (aircraft.getWingsPosition() != null) {
            model.addAttribute("wingsPosition", quizService.getParametersToQuiz(aircraft.getWingsPosition(), Arrays.asList(WingsPosition.values())));
        }
        if (aircraft.getPassengers() != null) {
            model.addAttribute("passengers", quizService.getParametersToQuiz(aircraft.getPassengers(), Arrays.asList(Passengers.values())));
        }
        if (aircraft.getAssignment() != null) {
            model.addAttribute("assignment", Assignment.values());
        }
        if (aircraft.getBody() != null) {
            model.addAttribute("body", Body.values());
        }
        if (aircraft.getWakeTurbulenceCategory() != null) {
            model.addAttribute("wtc", WakeTurbulenceCategory.values());
        }
        if (aircraft.getWings() != null) {
            model.addAttribute("wings", Wings.values());
        }
        quizService.randomCeiling(aircraft, model);
        quizService.randomCrew(aircraft, model);
        quizService.randomEnginesLocation(aircraft, model);
        quizService.randomMaxSpeed(aircraft, model);
        quizService.randomNames(aircraft, model);
        quizService.randomEnginesNumber(aircraft, model);
        quizService.randomManufacturer(aircraft, model);
        quizService.randomRange(aircraft, model);
        quizService.randomRateOfClimb(aircraft, model);
        model.addAttribute("image", aircraftService.image(aircraft));
        model.addAttribute("aircraft", new Aircraft());
        return "quiz/quiz";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam Long id, Aircraft aircraft, @AuthenticationPrincipal CurrentUser currentUser, Model model) throws NullPointerException {
        User user = currentUser.getUser();
        Set<Aircraft> userFamiliarAircraftList = user.getAircraft();
        Aircraft aircraftFromDB = aircraftRepository.findById(id).get();
        Map<String, String> wrongAnswers = new HashMap<>();
        if (!aircraftFromDB.equals(aircraft)) {
            if (!aircraftFromDB.getName().equals(aircraft.getName())) {
                wrongAnswers.put("Name", aircraftFromDB.getName());
            }
            if (!aircraftFromDB.getManufacturer().equals(aircraft.getManufacturer())) {
                wrongAnswers.put("Manufacturer", aircraftFromDB.getManufacturer());
            }
            if (aircraft.getAssignment() != null) {
                if (!aircraftFromDB.getAssignment().equals(aircraft.getAssignment())) {
                    wrongAnswers.put("Assignment", aircraftFromDB.getAssignment().toString());
                }
            }
            if (aircraft.getAircraftRole() != null) {
                log.info("role{}", aircraft.getAircraftRole().toString());
                if (!aircraftFromDB.getAircraftRole().equals(aircraft.getAircraftRole())) {
                    wrongAnswers.put("Aircraft role", aircraftFromDB.getAircraftRole().toString()
                    );
                }
            }

            if (aircraft.getBody() != null) {
                if (!aircraftFromDB.getBody().equals(aircraft.getBody())) {
                    wrongAnswers.put("Body", aircraftFromDB.getBody().toString());
                }
            }
            if (aircraft.getWings() != null) {
                if (!aircraftFromDB.getWings().equals(aircraft.getWings())) {
                    wrongAnswers.put("Wings", aircraftFromDB.getWings().toString());
                }
            }

            if (aircraft.getWingsPosition() != null) {

                if (!aircraftFromDB.getWingsPosition().equals(aircraft.getWingsPosition())) {
                    wrongAnswers.put("Wings position", aircraftFromDB.getWingsPosition().toString());
                }
            }
            if (aircraft.getTail() != null) {
                if (!aircraftFromDB.getTail().equals(aircraft.getTail())) {
                    wrongAnswers.put("Tail", aircraftFromDB.getTail().toString());
                }
            }
            if (aircraft.getEnginesType() != null) {
                if (!aircraftFromDB.getEnginesType().equals(aircraft.getEnginesType())) {
                    wrongAnswers.put("Engines type", aircraftFromDB.getEnginesType().toString());
                }
            }
            if (aircraft.getNumberOfEngines() != null) {
                if (!aircraftFromDB.getNumberOfEngines().equals(aircraft.getNumberOfEngines())) {
                    wrongAnswers.put("Engines quantity", aircraftFromDB.getNumberOfEngines().toString());
                }
            }
            if (aircraft.getEnginesLocation() != null) {
                if (!aircraftFromDB.getEnginesLocation().equals(aircraft.getEnginesLocation())) {
                    wrongAnswers.put("Engines location", aircraftFromDB.getEnginesLocation());
                }
            }

            if (aircraft.getCrew() != null) {

                if (!aircraftFromDB.getCrew().equals(aircraft.getCrew())) {
                    wrongAnswers.put("Crew", aircraftFromDB.getCrew().toString());
                }

            }
            if (aircraft.getPassengers() != null) {
                if (!aircraftFromDB.getPassengers().equals(aircraft.getPassengers())) {
                    wrongAnswers.put("Passengers", aircraftFromDB.getPassengers().toString());
                }
            }
            if (aircraft.getWakeTurbulenceCategory() != null) {

                if (!aircraftFromDB.getWakeTurbulenceCategory().equals(aircraft.getWakeTurbulenceCategory())) {
                    wrongAnswers.put("Wake turbulence category", aircraftFromDB.getWakeTurbulenceCategory().toString());
                }
            }
            if (aircraft.getRateOfClimb() != null) {
                if (!aircraftFromDB.getRateOfClimb().equals(aircraft.getRateOfClimb())) {
                    wrongAnswers.put("Rate of climb", aircraftFromDB.getRateOfClimb().toString());
                }
            }
            if (aircraft.getRangeOfAircraft() != null) {

                if (!aircraftFromDB.getRangeOfAircraft().equals(aircraft.getRangeOfAircraft())) {
                    wrongAnswers.put("Range", aircraftFromDB.getRangeOfAircraft().toString());
                }
            }
            if (aircraft.getMaxSpeed() != null) {

                if (!aircraftFromDB.getMaxSpeed().equals(aircraft.getMaxSpeed())) {
                    wrongAnswers.put("Max speed", aircraftFromDB.getMaxSpeed().toString());
                }
            }
            if (aircraft.getCeiling() != null) {

                if (!aircraftFromDB.getCeiling().equals(aircraft.getCeiling())) {
                    wrongAnswers.put("Ceiling", aircraftFromDB.getCeiling().toString());
                }
            }
        }
        if (wrongAnswers.isEmpty()) {
            model.addAttribute("familiar", quizService.addToFamiliarAircraftList(user, aircraftFromDB));
        } else {
            model.addAttribute("wrongAnswers", wrongAnswers);
            model.addAttribute("familiar", quizService.removeFromFamiliarAircraftList(user, aircraftFromDB));
        }

        return "quiz/results";
    }
}
