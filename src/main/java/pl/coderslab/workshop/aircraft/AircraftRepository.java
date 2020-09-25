package pl.coderslab.workshop.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.workshop.model.Aircraft;
import java.util.List;
import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Optional<Aircraft> findById(Long id);

    @Query(value = "select * from project.aircraft order by created desc limit 3", nativeQuery = true)
    List<Aircraft> lastAdded();

    @Query(value = "select * from project.aircraft where file is not null order by rand() limit 1", nativeQuery = true)
    Aircraft randomWithImage();

    @Query(value = "select distinct name from project.aircraft where name is not null and name != ?1 order by rand() limit 3", nativeQuery = true)
    List<String> randomNames(String name);

    @Query(value = "select distinct ceiling from project.aircraft where ceiling is not null and ceiling != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomCeiling(Integer ceiling);

    @Query(value = "select distinct crew from project.aircraft where crew is not null and crew != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomCrew(Integer crew);

    @Query(value = "select distinct engines_location from project.aircraft where engines_location is not null and engines_location != ?1 order by rand() limit 3", nativeQuery = true)
    List<String> randomEnginesLocation(String location);

    @Query(value = "select distinct manufacturer from project.aircraft where manufacturer is not null and manufacturer != ?1 order by rand() limit 3", nativeQuery = true)
    List<String> randomManufacturer(String manufacturer);

    @Query(value = "select distinct max_speed from project.aircraft where max_speed is not null and max_speed != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomMaxSpeed(Integer speed);

    @Query(value = "select distinct number_of_engines from project.aircraft where number_of_engines is not null and number_of_engines != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomNumberOfEngines(Integer number);

    @Query(value = "select distinct range_of_aircraft from project.aircraft where range_of_aircraft is not null and range_of_aircraft != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomRange(Integer aircraftRange);

    @Query(value = "select distinct rate_of_climb from project.aircraft where rate_of_climb is not null and rate_of_climb != ?1 order by rand() limit 3", nativeQuery = true)
    List<Integer> randomRateOfClimb(Integer roc);

    @Query(value = "select * from project.aircraft join project.user_aircraft ua on aircraft.id = ua.aircraft_id where ua.user_id=?1", nativeQuery = true)
    List<Aircraft> familiarAircraft(Long id);






}
