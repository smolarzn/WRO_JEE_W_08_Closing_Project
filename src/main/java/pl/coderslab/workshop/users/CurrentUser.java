package pl.coderslab.workshop.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.workshop.users.User user;

    public CurrentUser(String userName, String password, Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.workshop.users.User user) {
        super(userName, password, authorities);
        this.user = user;
    }

    public pl.coderslab.workshop.users.User getUser() {
        return user;
    }
}
