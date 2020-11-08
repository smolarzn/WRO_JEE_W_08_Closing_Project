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
        } else {
            return null;
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

//    protected Map<String,String> addToFamiliarAircraftList(User user, Aircraft aircraft) {
//        Set<Aircraft> familiarAircraft = user.getAircraft();
//        log.info("FAMILIAR PRZED ADD - familiar przed add {}", familiarAircraft.size());
//        log.info("NAME AIRCRAFTA TO ADD -name {}",aircraft.getName());
//        for (Aircraft a : familiarAircraft) {
//            if (a.getName().equals(aircraft.getName())) {
//                log.info("W FAMILIAR JEST JUŻ O TAKIM NAME - name {}", a.getName());
//                return familiarAircraftList(user);
//            }
//        }
//        if (user.getAircraft().add(aircraft)) {
//            log.info("NAME AIRCRAFTA TO ADD - NAME {}", aircraft.getName());
//            log.info("POWINNO DODAĆ DO FAMILIAR - familiar po add {}", familiarAircraft.size());
//
//            userRepository.save(user);
//            log.info("czy dodało?? {}", user.getAircraft().size());
//        }
//       return familiarAircraftList(user);
//    }

    protected void addToFamiliarAircraftList(User user, Aircraft aircraft) {
        Set<Aircraft> familiarAircraft = user.getAircraft();
        log.info("FAMILIAR PRZED ADD - familiar przed add {}", familiarAircraft.size());
        log.info("NAME AIRCRAFTA TO ADD -name {}", aircraft.getName());
        for (Aircraft a : familiarAircraft) {
            if (!a.getName().equals(aircraft.getName())) {
                log.info("W FAMILIAR JEST JUŻ O TAKIM NAME - name {}", a.getName());
                aircraftRepository.addToUserAircraft(user.getId(), aircraft.getId());
            }
        }
        log.info("NAME AIRCRAFTA TO ADD - NAME {}", aircraft.getName());
        log.info("POWINNO DODAĆ DO FAMILIAR - familiar po add {}", user.getAircraft().size());
    }


    //
//    protected Map<String,String> removeFromFamiliarAircraftList(User user, Aircraft aircraft) {
//        Set<Aircraft> familiarAircraft = user.getAircraft();
//        log.info("FAMILIAR PRZED REMOVE {}", familiarAircraft.size());
//        log.info("NAME AIRCRAFTA TO REMOVE -name {}",aircraft.getName());
//
//        for (Aircraft a : familiarAircraft) {
//            if (a.getName().equals(aircraft.getName())) {
//                if (user.getAircraft().remove(a)) {
//                    log.info("FAMILIAR PO REMOVE {}", familiarAircraft.size());
//                    userRepository.save(user);
//                    log.info(" dodalo do bazy? {}", user.getAircraft().size());
//
//                    return familiarAircraftList(user);
//                }
//            }
//
//        }
//        log.info("FAMILIAR BEZ REMOVE {} ", familiarAircraft.size());
//        return familiarAircraftList(user);
//    }
    protected void removeFromFamiliarAircraftList(User user, Aircraft aircraft) {
        Set<Aircraft> familiarAircraft = user.getAircraft();
        log.info("FAMILIAR PRZED REMOVE {}", familiarAircraft.size());
        log.info("NAME AIRCRAFTA TO REMOVE -name {}", aircraft.getName());

        for (Aircraft a : familiarAircraft) {
            if (a.getName().equals(aircraft.getName())) {
                aircraftRepository.removeFromUserAircraft(user.getId(), aircraft.getId());
                log.info("FAMILIAR PO REMOVE {}", user.getAircraft().size());
            }
        }
    }

}
