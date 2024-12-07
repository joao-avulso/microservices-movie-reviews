package br.com.czerwony.trabalho_sd_review_ms.consumers;

import br.com.czerwony.trabalho_sd_review_ms.dtos.ReviewRecordDto;
import br.com.czerwony.trabalho_sd_review_ms.models.ReviewModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import br.com.czerwony.trabalho_sd_review_ms.services.ReviewService;

@Component
public class ReviewConsumer {

    final ReviewService reviewService;

    public ReviewConsumer(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RabbitListener(queues = "${broker.queue.review.name}")
    public void listenReviewQueue(@Payload ReviewRecordDto reviewRecordDto) {
        var reviewModel = new ReviewModel();
        BeanUtils.copyProperties(reviewRecordDto, reviewModel);
        System.out.println(reviewService.save(reviewModel));
    }
}
