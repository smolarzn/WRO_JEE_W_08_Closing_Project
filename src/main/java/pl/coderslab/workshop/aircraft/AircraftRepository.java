package pl.coderslab.workshop.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.model.Aircraft;
import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Optional<Aircraft> findById(Long id);

}
