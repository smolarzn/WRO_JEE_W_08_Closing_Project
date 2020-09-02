package pl.coderslab.workshop.users;

import lombok.AllArgsConstructor;
import pl.coderslab.workshop.model.User;

@AllArgsConstructor
public class UserService {
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }
}
