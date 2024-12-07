package br.com.czerwony.trabalho_sd_review_ms.producers;

import br.com.czerwony.trabalho_sd_review_ms.dtos.EmailDto;
import br.com.czerwony.trabalho_sd_review_ms.models.ReviewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReviewProducer {

    final RabbitTemplate rabbitTemplate;

    public ReviewProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishReviewEmail(ReviewModel reviewModel) {
        var emailDto = new EmailDto();
        emailDto.setId(reviewModel.getUserId());
        emailDto.setEmailPara(reviewModel.getUserEmail());
        emailDto.setAssunto("Análise do filme " + reviewModel.getMovieTitle() + " publicada!");

        String star = "★";
        String stars = "";

        for (int i = 0; i < reviewModel.getStars(); i++) {
            stars = stars.concat(star);
        }

        emailDto.setTexto("Olá, sua análise do filme " + reviewModel.getMovieTitle() + " foi publicada com sucesso!\n\n" +
                "\"" + reviewModel.getContent() + "\"" + "\n\n" + stars);

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
