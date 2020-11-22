package pl.coderslab.workshop.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String firstname);

    Optional<User> findById(Long id);

    User findByEmail(String email);

    User save(User user);
}
