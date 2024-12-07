package br.com.czerwony.trabalho_sd_user_ms.producers;

import br.com.czerwony.trabalho_sd_user_ms.dtos.EmailDto;
import br.com.czerwony.trabalho_sd_user_ms.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setId(userModel.getId());
        emailDto.setEmailPara(userModel.getEmail());
        emailDto.setAssunto("Cadastro se cadastro na plataforma de correio de filmes realizado com sucesso!");
        emailDto.setTexto("Olá " + userModel.getPrimeiroNome() + " " + userModel.getSegundoNome() + ", seja bem vindo(a) ao Correio de Filmes!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
