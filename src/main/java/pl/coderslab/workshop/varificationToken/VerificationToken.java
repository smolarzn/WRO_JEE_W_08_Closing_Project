package pl.coderslab.workshop.varificationToken;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.coderslab.workshop.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;


    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate(EXPIRATION);
    }

    private LocalDateTime expiryDate(int expiryTime) {
        return LocalDateTime.now().plusMinutes(expiryTime);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExpiryDate() == null) ? 0 : getExpiryDate().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final VerificationToken otherToken = (VerificationToken) object;
        if (getExpiryDate() == null) {
            if (otherToken.getExpiryDate() != null) {
                return false;
            }
        } else if (!getExpiryDate().equals(otherToken.getExpiryDate())) {
            return false;
        }
        if (getToken() == null) {
            if (otherToken.getToken() != null) {
                return false;
            }
        } else if (!getToken().equals(otherToken.getToken())) {
            return false;
        }
        if (getUser() == null) {
            if (otherToken.getUser() != null) {
                return false;
            }
        } else if (!getUser().equals(otherToken.getUser())) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }




}
