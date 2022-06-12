package com.sesac.foodtruck.notificaion.ui.controller;

import com.sesac.foodtruck.notificaion.applicaion.service.NotificationService;
import com.sesac.foodtruck.notificaion.infrastructure.query.http.dto.Result;
import com.sesac.foodtruck.notificaion.ui.dto.Response;
import com.sesac.foodtruck.notificaion.ui.dto.Yn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationApiController {

    private final NotificationService notificationService;
    private final Response response;

    /**
     * 읽지 않은 알림 개수
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/06/12
    **/
    @GetMapping("/api/v1/notification/counts/{user_id}")
    public ResponseEntity<?> getNotificationCounts(@PathVariable("user_id") Long userId) {
        Yn readYn = Yn.N;
        Long counts = notificationService.findNotificationCounts(userId, readYn);

        return response.success(counts);
    }
}
