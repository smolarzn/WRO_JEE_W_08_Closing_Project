package pl.coderslab.workshop.model;

import lombok.*;
import pl.coderslab.workshop.model.aircraftProperties.*;
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
    private String name;
    @Size(min = 2, max = 50)
    @NotNull
    private String manufacturer;
    @Enumerated(value = EnumType.STRING)
    private Assignment assignment;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Body body;
    @Enumerated(value = EnumType.STRING)
    private WakeTurbulenceCategory wakeTurbulenceCategory;
    @Enumerated(value = EnumType.STRING)
    private Wings wings;
    @Enumerated(value = EnumType.STRING)
    private WingsPosition wingsPosition;
    @Enumerated(value = EnumType.STRING)
    private Tail tail;
    @Positive
    private Integer numberOfEngines;
    @Enumerated(value = EnumType.STRING)
    private EnginesType enginesType;
    private String enginesLocation;
    @Positive
    private Integer crew;
    @Positive
    private Integer passengers;
    @Positive
    private Integer maxSpeed;
    @Positive
    private Integer rangeOfAircraft;
    @Positive
    private Integer ceiling;
    @Positive
    private Integer rateOfClimb;
//    @Column
//    @Lob
//    private byte[] file;

}
