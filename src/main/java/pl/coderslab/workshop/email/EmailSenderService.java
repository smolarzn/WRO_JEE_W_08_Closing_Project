package pl.coderslab.workshop.email;

import javax.mail.MessagingException;

public interface EmailSenderService {

    void sendEmail(String userEmail, String subject, String userName, String templatePath) throws MessagingException;
}
