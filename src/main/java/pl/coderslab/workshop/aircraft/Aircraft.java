package pl.coderslab.workshop.aircraft;

import lombok.*;
import pl.coderslab.workshop.aircraft.aircraftProperties.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
        if (getClass() != o.getClass()) {
            return false;
        }
        final Aircraft otherAircraft = (Aircraft) o;
        if (getName() == null) {
            if (otherAircraft.getName() != null) {
                return false;
            }
        } else if (!getName().equals(otherAircraft.getName())) {
            return false;
        }
        if (getAssignment() == null) {
            if (otherAircraft.getAssignment() != null) {
                return false;
            }
        } else if (!getAssignment().equals(otherAircraft.getAssignment())) {
            return false;
        }
        if (getAircraftRole() == null) {
            if (otherAircraft.getAircraftRole() != null) {
                return false;
            }
        } else if (!getAircraftRole().equals(otherAircraft.getAircraftRole())) {
            return false;
        }
        if (getRangeOfAircraft() == null) {
            if (otherAircraft.getRangeOfAircraft() != null) {
                return false;
            }
        } else if (!getRangeOfAircraft().equals(otherAircraft.getRangeOfAircraft())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int hash = 5;
        int result = 1;
        result = hash * result + ((getName() == null) ? 0 : getName().hashCode());
        result = hash * result + ((getRangeOfAircraft() == null) ? 0 : getRangeOfAircraft().hashCode());
        result = hash * result + ((getAircraftRole() == null) ? 0 : getAircraftRole().hashCode());
        result = hash * result + ((getAssignment() == null) ? 0 : getAssignment().hashCode());
        return result;
    }


}
