package pl.coderslab.workshop.visitor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.coderslab.workshop.users.User;
import pl.coderslab.workshop.users.UserService;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;

    private final MessageSource messageSource;

    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, token);
        mailSender.send(email);

    }

    private SimpleMailMessage constructEmailMessage(OnRegistrationCompleteEvent event, String token) {
        User user = event.getUser();
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token + "&id=" + user.getId();
        final String message = messageSource.getMessage("message.registration", null, "To confirm your registration click on the below link", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Registration Confirmation");
        email.setText(message + " \r\n" + "http://localhost:8080" + confirmationUrl);
        return email;
    }


}
