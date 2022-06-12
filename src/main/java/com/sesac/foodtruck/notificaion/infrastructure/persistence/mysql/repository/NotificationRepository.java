package com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.repository;

import com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.entity.Notification;
import com.sesac.foodtruck.notificaion.ui.dto.Yn;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId, Sort sort);

    long countByUserIdAndReadYn(Long userId, Yn readYn);
}
