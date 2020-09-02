package pl.coderslab.workshop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Assignment assignment;
    private String role;
    private String nationalOrigin;
    @Min(1)
    private Integer numberOfEngines;
    private String typeOfEngines;
    private String enginesLocation;
    @Min(value = 1)
    private Integer crew;
    private Integer passengers;
    private String maxSpeed;
    private String rangeOfAircraft;
    private String ceiling;
    private String rateOfClimb;

}
