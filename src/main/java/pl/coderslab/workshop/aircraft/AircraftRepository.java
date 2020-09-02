package pl.coderslab.workshop.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.model.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {


}
