package pl.coderslab.workshop.varificationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.users.User;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
