package pl.coderslab.workshop.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.workshop.model.Aircraft;
import pl.coderslab.workshop.model.aircraftProperties.AircraftRole;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Optional<Aircraft> findById(Long id);

    List<Aircraft> findAllByAircraftRole(AircraftRole role);

    @Query(value = "select * from project.aircraft order by rand() limit 1", nativeQuery = true)
    Aircraft findRandom();


}
