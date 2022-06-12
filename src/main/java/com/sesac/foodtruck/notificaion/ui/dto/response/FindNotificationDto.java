package com.sesac.foodtruck.notificaion.ui.dto.response;

import com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.entity.Notification;
import com.sesac.foodtruck.notificaion.ui.dto.Yn;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(staticName = "of")
public class FindNotificationDto {
    private Long id;
    private Long userId;
    private String message;
    private String title;
    private Yn readYn;
    private LocalDateTime createdAt;

    public FindNotificationDto(Notification notification) {
        this.id = notification.getId();
        this.userId = notification.getUserId();
        this.message = notification.getMessage();
        this.title = notification.getTitle();
        this.readYn = notification.getReadYn();
        this.createdAt = notification.getCreatedAt();
    }
}
