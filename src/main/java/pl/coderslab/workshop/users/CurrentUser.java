package pl.coderslab.workshop.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.workshop.model.User user;

    public CurrentUser(String userName, String password, Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.workshop.model.User user) {
        super(userName, password, authorities);
        this.user = user;
    }

    public pl.coderslab.workshop.model.User getUser() {
        return user;
    }
}
