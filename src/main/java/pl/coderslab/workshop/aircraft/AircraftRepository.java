package pl.coderslab.workshop.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.workshop.model.Aircraft;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Optional<Aircraft> findById(Long id);

    @Query(value = "select * from project.aircraft where file is not null order by rand() limit 1", nativeQuery = true)
    Aircraft randomWithImage();

    @Query(value = "select distinct name from project.aircraft where name is not null and name != ?1 order by rand() limit 3", nativeQuery = true)
    List<String> randomNames(String name);

    @Query(value = "select distinct range_of_aircraft from project.aircraft where range_of_aircraft is not null and range_of_aircraft != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomRange(Integer aircraftRange);

    @Query(value = "select * from project.aircraft join project.user_aircraft ua on aircraft.id = ua.aircraft_id where ua.user_id=?1", nativeQuery = true)
    List<Aircraft> familiarAircraft(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into project.user_aircraft (user_id, aircraft_id) VALUES (?1, ?2)", nativeQuery = true)
    void addToUserAircraft(Long userId, Long aircraftId);

    @Modifying
    @Transactional
    @Query(value = "delete from project.user_aircraft where user_id=?1 and aircraft_id=?2", nativeQuery = true)
    void removeFromUserAircraft(Long userId, Long aircraftId);

}
