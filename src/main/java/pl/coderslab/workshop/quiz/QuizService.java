package pl.coderslab.workshop.quiz;

import org.springframework.stereotype.Service;
import pl.coderslab.workshop.model.aircraftProperties.AircraftRole;

import java.util.*;

@Service
public class QuizService {

    protected List<Enum> getParametersToQuiz(Enum parameter, List<Enum> enuList) {
        List<Enum> listToQuiz = new ArrayList<>();
        Random r = new Random();
        int i = 0;
        while (i < 3) {
            int random = r.nextInt(enuList.size());
            Enum addToList = enuList.get(random);
            if (!listToQuiz.equals(parameter) && !listToQuiz.contains(addToList)) {
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


}
