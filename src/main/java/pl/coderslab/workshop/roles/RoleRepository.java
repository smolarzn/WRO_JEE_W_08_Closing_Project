package pl.coderslab.workshop.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
