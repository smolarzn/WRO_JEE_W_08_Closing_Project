package pl.coderslab.workshop.aircraft;


import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.workshop.model.Aircraft;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AircraftServiceTest {
    public AircraftService service;

    @Test
    public void should() {
        //given
        Aircraft aircraft = new Aircraft();
        Aircraft aircraftMock = new Aircraft();
        aircraft.setId(1L);
        aircraft.setNumberOfEngines(0);

        AircraftRepository aircraftRepositoryMock = Mockito.mock(AircraftRepository.class);
        Mockito.when(aircraftRepositoryMock.save(aircraft)).thenReturn(aircraftMock);
        service = new AircraftService(aircraftRepositoryMock);

        //when
        Aircraft savedAircraft = service.save(aircraft);

        //then
        assertThat(savedAircraft.getId(), CoreMatchers.nullValue());

    }
}