package pl.coderslab.workshop.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.workshop.aircraft.AircraftRepository;
import pl.coderslab.workshop.aircraft.AircraftService;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.User;
import pl.coderslab.workshop.users.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {

    private final AircraftRepository aircraftRepository;
    private final AircraftService aircraftService;
    private final UserRepository userRepository;

    protected List<Enum> getParametersToQuiz(Enum parameter, List<Enum> enuList) {
        List<Enum> listToQuiz = new ArrayList<>();
        Random r = new Random();
        int i = 0;
        while (i < 3) {
            int random = r.nextInt(enuList.size());
            Enum addToList = enuList.get(random);
            if (!addToList.equals(parameter) && !listToQuiz.contains(addToList)) {
                listToQuiz.add(addToList);
                i++;
            }
        }
        if (parameter != null) {
            listToQuiz.add(parameter);
        }
        Collections.shuffle(listToQuiz);
        return listToQuiz;
    }

    protected void randomRange(Aircraft a, Model model) {
        if (a.getRangeOfAircraft() != null) {
            List<Integer> range = aircraftRepository.randomRange(a.getRangeOfAircraft());
            range.add(a.getRangeOfAircraft());
            Collections.shuffle(range);
            model.addAttribute("range", range);
        }
    }

    protected void randomCeiling(Aircraft a, Model model) {
        if (a.getCeiling() != null) {
            List<Integer> ceilings = aircraftRepository.randomCeiling(a.getCeiling());
            ceilings.add(a.getCeiling());
            Collections.shuffle(ceilings);
            model.addAttribute("ceiling", ceilings);
        }
    }

    protected void randomCrew(Aircraft a, Model model) {
        if (a.getCrew() != null) {
            List<Integer> crew = aircraftRepository.randomCrew(a.getCrew());
            crew.add(a.getCrew());
            Collections.shuffle(crew);
            model.addAttribute("crew", crew);
        }
    }

    protected void randomEnginesLocation(Aircraft a, Model model) {
        if (a.getEnginesLocation() != null) {
            List<String> enginesLocation = aircraftRepository.randomEnginesLocation(a.getEnginesLocation());
            enginesLocation.add(a.getEnginesLocation());
            Collections.shuffle(enginesLocation);
            model.addAttribute("enginesLocation", enginesLocation);
        }
    }

    protected void randomManufacturer(Aircraft a, Model model) {
        if (a.getManufacturer() != null) {
            List<String> manufacturers = aircraftRepository.randomManufacturer(a.getManufacturer());
            manufacturers.add(a.getManufacturer());
            Collections.shuffle(manufacturers);
            model.addAttribute("manufacturer", manufacturers);
        }
    }

    protected void randomMaxSpeed(Aircraft a, Model model) {
        if (a.getMaxSpeed() != null) {
            List<Integer> maxSpeed = aircraftRepository.randomMaxSpeed(a.getMaxSpeed());
            maxSpeed.add(a.getMaxSpeed());
            Collections.shuffle(maxSpeed);
            model.addAttribute("maxSpeed", maxSpeed);
        }
    }

    protected void randomEnginesNumber(Aircraft a, Model model) {
        if (a.getNumberOfEngines() != null) {
            List<Integer> enginesNumber = aircraftRepository.randomNumberOfEngines(a.getNumberOfEngines());
            enginesNumber.add(a.getNumberOfEngines());
            Collections.shuffle(enginesNumber);
            model.addAttribute("enginesNumber", enginesNumber);
        }

    }

    protected void randomRateOfClimb(Aircraft real, Model model) {
        if (real.getRateOfClimb() != null) {
            List<Integer> rateOfClimb = aircraftRepository.randomRateOfClimb(real.getRateOfClimb());
            rateOfClimb.add(real.getRateOfClimb());
            Collections.shuffle(rateOfClimb);
            model.addAttribute("rateOfClimb", rateOfClimb);
        }
    }

    protected void randomNames(Aircraft a, Model model) {
        String name = a.getName();
        if (name != null) {
            List<String> names = aircraftRepository.randomNames(a.getName());
            names.add(a.getName());
            Collections.shuffle(names);
            model.addAttribute("name", names);
        }
    }

    protected Map<String, String> familiarAircraftList(User user) {
        Long id = user.getId();
        List<Aircraft> aircrafts = aircraftRepository.familiarAircraft(id);
        Map<String, String> familiar = new HashMap<>();
        for (Aircraft a : aircrafts) {
            familiar.put(a.getName(), aircraftService.image(a));
        }
        return familiar;
    }

    protected Map<String,String> addToFamiliarAircraftList(User user, Aircraft aircraft) {
        Set<Aircraft> familiarAircraft = user.getAircraft();
        log.info("FAMILIAR PRZED ADD - familiar przed add {}", familiarAircraft.size());
        log.info("NAME AIRCRAFTA TO ADD -name {}",aircraft.getName());
        for (Aircraft a : familiarAircraft) {
            if (a.getName().equals(aircraft.getName())) {
                log.info("W FAMILIAR JEST JUŻ O TAKIM NAME - name {}", a.getName());
                return familiarAircraftList(user);
            }
        }
        if (user.getAircraft().add(aircraft)) {
            log.info("NAME AIRCRAFTA TO ADD - NAME {}", aircraft.getName());
            log.info("POWINNO DODAĆ DO FAMILIAR - familiar po add {}", familiarAircraft.size());

            userRepository.save(user);
            log.info("czy dodało?? {}", user.getAircraft().size());
        }
       return familiarAircraftList(user);
    }

    protected Map<String,String> removeFromFamiliarAircraftList(User user, Aircraft aircraft) {
        Set<Aircraft> familiarAircraft = user.getAircraft();
        log.info("FAMILIAR PRZED REMOVE {}", familiarAircraft.size());
        log.info("NAME AIRCRAFTA TO REMOVE -name {}",aircraft.getName());

        for (Aircraft a : familiarAircraft) {
            if (a.getName().equals(aircraft.getName())) {
                if (user.getAircraft().remove(a)) {
                    log.info("FAMILIAR PO REMOVE {}", familiarAircraft.size());
                    userRepository.save(user);
                    log.info(" dodalo do bazy? {}", user.getAircraft().size());

                    return familiarAircraftList(user);
                }
            }

        }
        log.info("FAMILIAR BEZ REMOVE {} ", familiarAircraft.size());
        return familiarAircraftList(user);
    }

}
