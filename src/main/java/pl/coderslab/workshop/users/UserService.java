package pl.coderslab.workshop.users;

import pl.coderslab.workshop.varificationToken.VerificationToken;

public interface UserService {

    User findByFirstName(String name);

    User saveUser(User user);

    void createVerificationTokenForUser(User user, String token);

    User findById(Long id);


    String validateVerificationToken(String token);

}
