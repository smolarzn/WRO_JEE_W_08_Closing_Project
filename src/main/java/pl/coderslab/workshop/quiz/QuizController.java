package pl.coderslab.workshop.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.aircraftProperties.AircraftRole;
import pl.coderslab.workshop.model.aircraftProperties.Tail;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class QuizController {

    private final AircraftRepository aircraftRepository;
    private final AircraftService aircraftService;
    private final QuizService quizService;


    @GetMapping("/quiz")
    public String quiz(Model model) {
        Aircraft aircraft = aircraftRepository.findRandom();
//        model.addAttribute("role", quizService.getAircraftRolesToQuiz(aircraft.getAircraftRole()));
        model.addAttribute("role", quizService.getParametersToQuiz(aircraft.getAircraftRole(), Arrays.asList(AircraftRole.values())));
        model.addAttribute("tail", quizService.getParametersToQuiz(aircraft.getTail(), Arrays.asList(Tail.values())));
        model.addAttribute("image", aircraftService.image(aircraft.getFile()));
        model.addAttribute("id", aircraft.getId());
        model.addAttribute("aircraft", new Aircraft());
        return "quiz/quiz";
    }

    @PostMapping("/quiz")
    @ResponseBody
    public String quizhandle(@RequestParam Long id, Aircraft aircraft) {
        Aircraft aircraft1 = aircraftRepository.findById(id).get();
        if (aircraft1.getAircraftRole() == (aircraft.getAircraftRole())) {
            return "okkkk";
        }
        return aircraft.getAircraftRole().toString();
    }
}
