package com.sesac.foodtruck.notificaion.applicaion.service;

import com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.entity.Notification;
import com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.repository.NotificationRepository;
import com.sesac.foodtruck.notificaion.infrastructure.query.http.dto.GetStoreResponse;
import com.sesac.foodtruck.notificaion.infrastructure.query.http.dto.StoreClient;
import com.sesac.foodtruck.notificaion.ui.dto.Yn;
import com.sesac.foodtruck.notificaion.ui.dto.response.FindNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final StoreClient storeClient;

    public Long findNotificationCounts(Long userId, Yn readYn) {
        return notificationRepository.countByUserIdAndReadYn(userId, readYn);
    }

    public List<FindNotificationDto> findNotificationByUserId(Long userId) {
        Order readYnAsc = Order.asc("readYn");
        Order createdAtDesc = Order.desc("createdAt");

        Sort sort = by(List.of(readYnAsc, createdAtDesc));
        return notificationRepository.findByUserId(userId, sort)
                .stream()
                .map(notification -> new FindNotificationDto(notification))
                .collect(Collectors.toList());
    }

    /**
     * 주문 신청
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/06/12
    **/
    @Transactional
    public void insertOrderPlaced(String authorization, Long userId, Long storeId) {
        GetStoreResponse storeResponse = storeClient.getStore(authorization, storeId).getData();

        String title = "주문이 신청되었습니다.";
        String storeName = "[" + storeResponse.getStoreName() + "]";
        String message = storeName + "매장의 주문이 신청되었습니다.";
        Notification notification = Notification.of(userId, title, message);

        notificationRepository.save(notification);
    }

    /**
     * 주문 수락
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/06/12
    **/
    @Transactional
    public void insertOrderAccepted(String authorization, Long userId, Long storeId) {
        GetStoreResponse storeResponse = storeClient.getStore(authorization, storeId).getData();

        String title = "주문이 수락되었습니다.";
        String storeName = "[" + storeResponse.getStoreName() + "]";
        String message = storeName + "매장의 주문이 수락되었습니다.";
        Notification notification = Notification.of(userId, title, message);

        notificationRepository.save(notification);
    }

    //TODO 조리 완료
}
