package br.com.czerwony.trabalho_sd_user_ms.producers;

import br.com.czerwony.trabalho_sd_user_ms.dtos.ReviewDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReviewProducer {

    final RabbitTemplate rabbitTemplate;

    public ReviewProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "default.review")
    private String routingKey;

    public void sendReview(ReviewDto review) {
        rabbitTemplate.convertAndSend("", routingKey, review);
    }


}
