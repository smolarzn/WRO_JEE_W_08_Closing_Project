package pl.coderslab.workshop.aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.workshop.model.Aircraft;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/aircraft")
public class AircraftController {

    private final AircraftRepository aircraftRepository;

    @GetMapping("/list")
    public String aircraftList(Model model) {
        model.addAttribute("aircraft", aircraftRepository.findAll());
        return "listOfAircraft";
    }

    @GetMapping("/details")
    public String detailsOfAircraft(@RequestParam Long id, Model model) {
        model.addAttribute("aircraft", aircraftRepository.findById(id).get());
        return "detailsOfAircraft";
    }



    @GetMapping("/add")
    public String addAircraft(Model model) {
        model.addAttribute("aircraft", new Aircraft());
        return "addAircraft";
    }

    @PostMapping("/add")
    public String addAircraft(@Valid Aircraft aircraft, BindingResult result) {
        if (result.hasErrors()) {
            return "addAircraft";
        }
        aircraftRepository.save(aircraft);
        return "redirect:/aircraft/list";
    }

    @GetMapping("/edit")
    public String editAircraft(@RequestParam Long id, Model model) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            model.addAttribute("aircraft", aircraft.get());
        }
        return "editAircraft";
    }

    @PostMapping("/edit")
    public String editAircraft(@Valid Aircraft aircraft, BindingResult result) {
        if (result.hasErrors()) {
            return "editAircraft";
        }
        aircraftRepository.save(aircraft);
        return "redirect:/aircraft/list";
    }

    @GetMapping("/delete")
    public String deleteAircraft(@RequestParam Long id, Model model) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            model.addAttribute("aircraft", aircraft.get());
        }
        return "deleteAircraft";
    }

    @PostMapping("/delete")
    public String deleteAircraft(@RequestParam Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        aircraft.ifPresent(aircraftRepository::delete);
        return "redirect:/aircraft/list";
    }
}
