package br.com.czerwony.trabalho_sd_email_ms.consumers;

import br.com.czerwony.trabalho_sd_email_ms.dtos.EmailRecordDto;
import br.com.czerwony.trabalho_sd_email_ms.models.EmailModel;
import br.com.czerwony.trabalho_sd_email_ms.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        emailModel.setUserId(emailRecordDto.id());
        System.out.println(emailService.enviarEmail(emailModel));
    }
}
