package pl.coderslab.workshop.model;

import lombok.*;
import pl.coderslab.workshop.model.aircraftProperties.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 50)
    @NotNull
    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Assignment assignment;
    @Enumerated(value = EnumType.STRING)
    private AircraftRole aircraftRole;

    @Positive
    private Integer rangeOfAircraft;

    @Column
    @Lob
    private byte[] file;

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (!(o instanceof Aircraft)) {
            return false;
        }
        final Aircraft aircraft = (Aircraft) o;

        return name.equals(aircraft.name) && assignment.equals(aircraft.assignment)
                && rangeOfAircraft == aircraft.rangeOfAircraft && this.aircraftRole.equals(aircraft.aircraftRole);

    }

    @Override
    public int hashCode() {
        return 17 + name.hashCode();
    }


}
