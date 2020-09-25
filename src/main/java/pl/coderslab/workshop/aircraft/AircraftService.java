package pl.coderslab.workshop.aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.workshop.model.Aircraft;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class AircraftService {

    private final AircraftRepository repository;

    public Aircraft save(Aircraft aircraft) {
        return repository.save(aircraft);
    }

    public String image(Aircraft aircraft) {
        byte[] file = aircraft.getFile();
        if (file != null && file.length > 0) {
            return Base64.getEncoder().encodeToString(file);
        }
        return null;
    }
}
