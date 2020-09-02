package pl.coderslab.workshop.aircraft;

import lombok.RequiredArgsConstructor;
import pl.coderslab.workshop.model.Aircraft;

@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository repository;

    public Aircraft save(Aircraft aircraft) {
        return aircraft;
    }


}
