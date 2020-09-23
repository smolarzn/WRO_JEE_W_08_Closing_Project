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
        Aircraft real = aircraftRepository.findById(id).get();
        Map<String, String> wrongAnswers = new HashMap<>();
        if (!real.equals(aircraft)) {
            if (!real.getName().equals(aircraft.getName())) {
                wrongAnswers.put(real.getName(), "name");
            }
            if (!real.getManufacturer().equals(aircraft.getManufacturer())) {
                wrongAnswers.put(real.getManufacturer(), "manufacturer");
            }
            if (aircraft.getAssignment() != null) {
                if (!real.getAssignment().equals(aircraft.getAssignment())) {
                    wrongAnswers.put("assignment", real.getAssignment().toString());
                }
            }
            if (aircraft.getAircraftRole() != null) {
                if (!real.getAircraftRole().equals(aircraft.getAircraftRole())) {
                    wrongAnswers.put(real.getAircraftRole().toString(), "aircraft role");
                }
            }

            if (aircraft.getBody() != null) {
                if (!real.getBody().equals(aircraft.getBody())) {
                    wrongAnswers.put("body", real.getBody().toString());
                }
            }
            if (aircraft.getWings() != null) {
                if (!real.getWings().equals(aircraft.getWings())) {
                    wrongAnswers.put("wings", real.getWings().toString());
                }
            }

            if (aircraft.getWingsPosition() != null) {

                if (!real.getWingsPosition().equals(aircraft.getWingsPosition())) {
                    wrongAnswers.put("wings position", real.getWingsPosition().toString());
                }
            }
            if (aircraft.getTail() != null) {
                if (!real.getTail().equals(aircraft.getTail())) {
                    wrongAnswers.put("tail", real.getTail().toString());
                }
            }
            if (aircraft.getEnginesType() != null) {
                if (!real.getEnginesType().equals(aircraft.getEnginesType())) {
                    wrongAnswers.put("engines type", real.getEnginesType().toString());
                }
            }
            if (aircraft.getNumberOfEngines() != null) {
                if (!real.getNumberOfEngines().equals(aircraft.getNumberOfEngines())) {
                    wrongAnswers.put("engines numbar", real.getNumberOfEngines().toString());
                }
            }
            if (aircraft.getEnginesLocation() != null) {
                if (!real.getEnginesLocation().equals(aircraft.getEnginesLocation())) {
                    wrongAnswers.put("engines location", real.getEnginesLocation());
                }
            }

            if (aircraft.getCrew() != null) {

                if (!real.getCrew().equals(aircraft.getCrew())) {
                    wrongAnswers.put("crew", real.getCrew().toString());
                }

            }
            if (aircraft.getPassengers() != null) {
                if (!real.getPassengers().equals(aircraft.getPassengers())) {
                    wrongAnswers.put("passengers", real.getPassengers().toString());
                }
            }
            if (aircraft.getWakeTurbulenceCategory() != null) {

                if (!real.getWakeTurbulenceCategory().equals(aircraft.getWakeTurbulenceCategory())) {
                    wrongAnswers.put("wake turbulence category", real.getWakeTurbulenceCategory().toString());
                }
            }
            if (aircraft.getRateOfClimb() != null) {
                if (!real.getRateOfClimb().equals(aircraft.getRateOfClimb())) {
                    wrongAnswers.put("rate of climb", real.getRateOfClimb().toString());
                }
            }
            if (aircraft.getRangeOfAircraft() != null) {

                if (!real.getRangeOfAircraft().equals(aircraft.getRangeOfAircraft())) {
                    wrongAnswers.put(real.getRangeOfAircraft().toString(), "range");
                }
            }
            if (aircraft.getMaxSpeed() != null) {

                if (!real.getMaxSpeed().equals(aircraft.getMaxSpeed())) {
                    wrongAnswers.put("max speed", real.getMaxSpeed().toString());
                }
            }
            if (aircraft.getCeiling() != null) {

                if (!real.getCeiling().equals(aircraft.getCeiling())) {
                    wrongAnswers.put("ceiling", real.getCeiling().toString());
                }
            }
        }
        User user = currentUser.getUser();
        Set<Aircraft> aircraft1 = user.getAircraft();

                model.addAttribute("wrongAnswers", wrongAnswers);
        if (wrongAnswers.isEmpty()) {
            if (!aircraft1.contains(real)) {
                aircraft1.add(real);
            }
        } else {
            log.info("IS NOT EMPTY");
            if (aircraft1.contains(real)) {
                log.info("CONTAINS REAL");
                aircraft1.remove(real);
            }
        }
        user.setAircraft(aircraft1);
        userRepository.save(user);
        Map<String, String> aircrafts = quizService.familiarAircraft(user);
        if (aircrafts!=null) {
            model.addAttribute("familiar", aircrafts);
        }
        return "quiz/results";
    }
}
