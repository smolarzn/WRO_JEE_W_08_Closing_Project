package pl.coderslab.workshop.model;

import lombok.*;
import pl.coderslab.workshop.model.validators.Password;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String email;
    @Column(nullable = false)
    @NotEmpty
//    @Password
    private String password;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
