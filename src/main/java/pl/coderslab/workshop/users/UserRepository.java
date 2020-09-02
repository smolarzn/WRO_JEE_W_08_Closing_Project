package pl.coderslab.workshop.users;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
