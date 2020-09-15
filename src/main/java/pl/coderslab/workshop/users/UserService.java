package pl.coderslab.workshop.users;

import pl.coderslab.workshop.model.User;

public interface UserService {

    User findByFirstName(String name);

    void saveUser(User user);
}
