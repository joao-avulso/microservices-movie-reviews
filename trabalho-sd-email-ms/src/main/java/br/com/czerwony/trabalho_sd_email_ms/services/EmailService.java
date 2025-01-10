package br.com.czerwony.trabalho_sd_email_ms.services;

import br.com.czerwony.trabalho_sd_email_ms.enums.StatusEmail;
import br.com.czerwony.trabalho_sd_email_ms.models.EmailModel;
import br.com.czerwony.trabalho_sd_email_ms.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailDe;

    @Transactional
    public EmailModel enviarEmail(EmailModel emailModel) {
        try {
            emailModel.setDataHoraEnvio(LocalDateTime.now());
            emailModel.setEmailDe(emailDe);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailPara());
            message.setSubject(emailModel.getAssunto());
            message.setText(emailModel.getTexto());
            mailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERRO);
        }

        return emailRepository.save(emailModel);
    }
}
