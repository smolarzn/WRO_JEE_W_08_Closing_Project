package pl.coderslab.workshop.aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.aircraftProperties.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/aircraft")
public class AircraftController {

    private final AircraftRepository aircraftRepository;
    private final AircraftService aircraftService;

    @ModelAttribute(name = "assignment")
    public List<Assignment> assignments() {
        return Arrays.asList(Assignment.values());
    }

    @ModelAttribute(name = "body")
    public List<Body> bodyList() {
        return Arrays.asList(Body.values());
    }

    @ModelAttribute(name = "enginesType")
    public List<EnginesType> enginesTypeList() {
        return Arrays.asList(EnginesType.values());
    }

    @ModelAttribute(name = "tail")
    public List<Tail> tailList() {
        return Arrays.asList(Tail.values());
    }

    @ModelAttribute(name = "WTC")
    public List<WakeTurbulenceCategory> wakeTurbulenceCategoryList() {
        return Arrays.asList(WakeTurbulenceCategory.values());
    }

    @ModelAttribute(name = "wings")
    public List<Wings> wingsList() {
        return Arrays.asList(Wings.values());
    }

    @ModelAttribute(name = "wingsPosition")
    public List<WingsPosition> wingsPositionList() {
        return Arrays.asList(WingsPosition.values());
    }

    @ModelAttribute(name = "aircraftRole")
    public List<AircraftRole> aircraftRoleList() {
        return Arrays.asList(AircraftRole.values());
    }

    @ModelAttribute(name = "passengers")
    public List<Passengers> passengers() {
        return Arrays.asList(Passengers.values());
    }

    @GetMapping("/list")
    public String aircraftList(Model model) {
        List<Aircraft> allAircraft = aircraftRepository.findAll();
        Map<Aircraft, String> aircraftImageMap = new HashMap<>();
        for (Aircraft a : allAircraft) {
            aircraftImageMap.put(a, aircraftService.image(a));
        }
        model.addAttribute("aircraft", aircraftImageMap);
        return "aircraft/list";
    }

    @GetMapping("/details")
    public String detailsOfAircraft(@RequestParam Long id, Model model) {
        Aircraft aircraft = aircraftRepository.findById(id).get();
        model.addAttribute("aircraft", aircraft);
        model.addAttribute("image", aircraftService.image(aircraft));
        return "aircraft/details";
    }

    @GetMapping("/admin/add")
    public String addAircraft(Model model) {
        model.addAttribute("aircraft", new Aircraft());
        return "aircraft/add";
    }

    @PostMapping("/admin/add")
    public String addAircraft(@Valid Aircraft aircraft, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "aircraft/add";
        }
        aircraftRepository.save(aircraft);
        model.addAttribute("aircraft", aircraft);
        return "aircraft/addImage";
    }

    @GetMapping("/admin/addImage")
    public String addImage(@RequestParam Long id, Model model) {
        Aircraft aircraft = aircraftRepository.findById(id).get();
        model.addAttribute("aircraft", aircraft);
        return "aircraft/addImage";

    }

    @PostMapping("/admin/addImage")
    public String adImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        Aircraft aircraft = aircraftRepository.findById(id).get();
        byte[] newImage = new byte[0];
        try {
            newImage = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (newImage.length > 0) {
            aircraft.setFile(newImage);
        }
        aircraftRepository.save(aircraft);
        return "redirect:/aircraft/list";
    }

    @GetMapping("/admin/edit")
    public String editAircraft(@RequestParam Long id, Model model) {
        Aircraft aircraft = aircraftRepository.findById(id).get();
        model.addAttribute("aircraft", aircraft);
        return "aircraft/edit";
    }

    @PostMapping("/admin/edit")
    public String editAircraft(@Valid Aircraft aircraft, BindingResult result) {
        if (result.hasErrors()) {
            return "aircraft/edit";
        }
        aircraftRepository.save(aircraft);
        return "redirect:/aircraft/list";
    }

    @GetMapping("/admin/delete")
    public String deleteAircraft(@RequestParam Long id, Model model) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            model.addAttribute("aircraft", aircraft.get());
        }
        return "aircraft/delete";
    }

    @PostMapping("/admin/delete")
    public String deleteAircraft(@RequestParam Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        aircraft.ifPresent(aircraftRepository::delete);
        return "redirect:/aircraft/list";
    }
}
