package pl.coderslab.workshop.aircraft;

import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.workshop.model.Aircraft;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/aircraft")
public class AircraftController {

    private final AircraftRepository aircraftRepository;

    @GetMapping("/list")
    public String aircraftList(Model model) {
        model.addAttribute("aircraft", aircraftRepository.findAll());
        return "aircraft/list";
    }

    @GetMapping("/details")
    public String detailsOfAircraft(@RequestParam Long id, Model model) {
        Aircraft aircraft = aircraftRepository.findById(id).get();
        model.addAttribute("aircraft", aircraft);
        byte[] file = aircraft.getFile();
        String image = "";
        if (file != null && file.length > 0) {
            image = Base64.getMimeEncoder().encodeToString(file);
        }
        model.addAttribute("image", image);
        return "aircraft/details";
    }

    @GetMapping("/add")
    public String addAircraft(Model model) {
        model.addAttribute("aircraft", new Aircraft());
        return "aircraft/add";
    }

    @PostMapping("/add")
    public String addAircraft(@Valid Aircraft aircraft, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "aircraft/add";
        }
        aircraftRepository.save(aircraft);
        model.addAttribute("aircraft", aircraft);
        return "aircraft/addImage";
    }

    @PostMapping("/addImage")
    public String adImage(@RequestParam("file") MultipartFile file, Aircraft aircraft) {
        try {
            aircraft.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
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
        return "aircraft/edit";
    }

    @PostMapping("/edit")
    public String editAircraft(@Valid Aircraft aircraft, BindingResult result) {
        if (result.hasErrors()) {
            return "aircraft/edit";
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
        return "aircraft/delete";
    }

    @PostMapping("/delete")
    public String deleteAircraft(@RequestParam Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        aircraft.ifPresent(aircraftRepository::delete);
        return "redirect:/aircraft/list";
    }
}
