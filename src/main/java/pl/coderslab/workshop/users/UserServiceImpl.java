package pl.coderslab.workshop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.workshop.roles.Role;
import pl.coderslab.workshop.varificationToken.VerificationToken;
import pl.coderslab.workshop.roles.RoleRepository;
import pl.coderslab.workshop.varificationToken.VerificationTokenRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expiredToken";
    public static final String TOKEN_VALID = "validToken";

    @Override
    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return  userRepository.findById(id).get();
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email)!=null;
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final LocalDateTime now = LocalDateTime.now();
        if (verificationToken.getExpiryDate().isBefore(now)) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        final User user = verificationToken.getUser();
        user.setEnabled(1);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
        return TOKEN_VALID;
    }

}
