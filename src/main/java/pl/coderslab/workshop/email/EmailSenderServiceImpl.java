package pl.coderslab.workshop.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(String email, String subject, String name, String templatePath) throws MessagingException {
        Context thymeleaf = new Context();
        thymeleaf.setVariable("name", name);
        String text = templateEngine.process(templatePath, thymeleaf);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);

        mailSender.send(mimeMessage);

    }


}
