package pl.coderslab.workshop.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import pl.coderslab.workshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String firstname);
}
