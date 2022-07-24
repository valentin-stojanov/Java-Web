package bg.softuni.mobilele.service;

import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public EmailService(TemplateEngine templateEngine,
                        JavaMailSender javaMailSender,
                        MessageSource messageSource) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
    }

    public void sendRegistrationEmail(String userEmail,
                                      String userName,
                                      Locale preferredLocale){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("mobilele@mobilele.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject(getEmailSubject(preferredLocale));
            mimeMessageHelper.setText(generateMessageContent(preferredLocale, userName),true);

            this.javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getEmailSubject(Locale locale){
        return this.messageSource.getMessage("registration_subject",
                new Object[0],
                locale);
    }

    private String generateMessageContent(Locale locale,
                                          String userName){
        Context ctx = new Context();
        ctx.setLocale(locale);
        ctx.setVariable("userName", userName);
        return this.templateEngine.process("email/registration", ctx);
    }
}
