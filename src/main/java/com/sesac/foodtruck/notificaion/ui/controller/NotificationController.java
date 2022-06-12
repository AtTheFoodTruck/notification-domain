package com.sesac.foodtruck.notificaion.ui.controller;

import com.sesac.foodtruck.notificaion.applicaion.service.NotificationService;
import com.sesac.foodtruck.notificaion.ui.dto.Response;
import com.sesac.foodtruck.notificaion.ui.dto.response.FindNotificationDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final Response response;

    @GetMapping("/notifications/{user_Id}")
    public ResponseEntity<?> getNotificationByUserId(@PathVariable("user_Id") Long userId) {
        List<FindNotificationDto> notifications = notificationService.findNotificationByUserId(userId);

        GetNotificationResponse resNotification = new GetNotificationResponse(notifications);

        return response.success(resNotification);
    }

    @Data
    static class GetNotificationResponse{

        private List<_Notification> notifications;

        public GetNotificationResponse(List<FindNotificationDto> findNotificationDtos) {
            this.notifications =
                    findNotificationDtos.stream().map(dto -> new _Notification(dto)).collect(Collectors.toList());
        }

        @Data
        static class _Notification {
            private Long id;
            private String title;
            private String message;
            private boolean readYn;
            private String time;

            public _Notification(FindNotificationDto dto) {
                this.id = dto.getId();
                this.title = dto.getTitle();
                this.message = dto.getMessage();
                this.readYn = dto.getReadYn().isFlag();
                this.time = dto.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
        }
    }
}
