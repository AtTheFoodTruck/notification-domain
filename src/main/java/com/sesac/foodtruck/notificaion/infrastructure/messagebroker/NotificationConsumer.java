package com.sesac.foodtruck.notificaion.infrastructure.messagebroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesac.foodtruck.notificaion.applicaion.service.NotificationService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    /**
     * 토픽: 주문 신청
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/06/12
    **/
    @Transactional
    @KafkaListener(topics = "orderPlaced")
    public void orderPlaced(HttpServletRequest request, String kafkaMessage) throws JsonProcessingException {
        String authorization = request.getHeader("Authorization");

        log.info("NotificationConsumer.orderPlaced");
        log.info("kafka Message = {} ", kafkaMessage);

        KafkaSendOrderDto kafkaSendOrderDto = objectMapper.readValue(kafkaMessage, KafkaSendOrderDto.class);

        notificationService.insertOrderPlaced(authorization, kafkaSendOrderDto.userId, kafkaSendOrderDto.storeId);
    }

    /**
     * 토픽: 주문 접수
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/06/12
    **/
    @Transactional
    @KafkaListener(topics = "orderAccepted")
    public void orderAccepted(HttpServletRequest request, String kafkaMessage) throws JsonProcessingException {
        String authorization = request.getHeader("Authorization");
        log.info("NotificationConsumer.orderAccepted");
        log.info("kafka Message = {} ", kafkaMessage);

        KafkaSendOrderDto kafkaSendOrderDto = objectMapper.readValue(kafkaMessage, KafkaSendOrderDto.class);

        notificationService.insertOrderAccepted(authorization, kafkaSendOrderDto.userId, kafkaSendOrderDto.storeId);

    }

    @Data
    @NoArgsConstructor
    static class KafkaSendOrderDto {
        private Long id;
        private Long userId;
        private Long storeId;
        private long orderPrice;
        private LocalDateTime orderTime;
        private OrderStatus orderStatus;

        @Builder
        public KafkaSendOrderDto(Long id, Long userId, Long storeId, long orderPrice, LocalDateTime orderTime, OrderStatus orderStatus) {
            this.id = id;
            this.userId = userId;
            this.storeId = storeId;
            this.orderPrice = orderPrice;
            this.orderTime = orderTime;
            this.orderStatus = orderStatus;
        }
    }

}
