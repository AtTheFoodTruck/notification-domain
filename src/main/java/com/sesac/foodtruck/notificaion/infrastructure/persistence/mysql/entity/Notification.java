package com.sesac.foodtruck.notificaion.infrastructure.persistence.mysql.entity;


import com.sesac.foodtruck.notificaion.ui.dto.Yn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private Long userId;
    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    private Yn readYn;

    /** 생성 메소드 **/
    public static Notification of(Long userId, String title, String message) {
        Notification notification = new Notification();
        notification.userId = userId;
        notification.title = title;
        notification.message = message;
        notification.readYn = Yn.N;

        return notification;
    }
}
