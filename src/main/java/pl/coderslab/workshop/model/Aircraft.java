package pl.coderslab.workshop.model;

import lombok.*;
import pl.coderslab.workshop.model.aircraftProperties.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aircraft")
//@EqualsAndHashCode(of = "id")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 50)
    @NotNull
    @Column(unique = true)
    private String name;
    @Size(min = 2, max = 50)
    @NotNull
    private String manufacturer;
    @Enumerated(value = EnumType.STRING)
    private Assignment assignment;
    @Enumerated(value = EnumType.STRING)
    private AircraftRole aircraftRole;
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
    @Enumerated(value = EnumType.STRING)
    private Passengers passengers;
    @Positive
    private Integer maxSpeed;
    @Positive
    private Integer rangeOfAircraft;
    @Positive
    private Integer ceiling;
    @Positive
    private Integer rateOfClimb;
    @Column
    @Lob
    private byte[] file;
    private LocalDate created;
    private LocalDate updated;
    @ManyToMany(mappedBy = "aircraft")
    private Set<User> users;
//    @OneToMany
//    private List<Image> images = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }
    @PreUpdate
    public void preUpdate() {
        updated = LocalDate.now();
    }



}
