package com.sesac.foodtruck.notificaion.ui.dto;

import lombok.Getter;

@Getter
public enum Yn {
    Y(true), N(false);

    private boolean flag;

    Yn(boolean flag) {
        this.flag = flag;
    }
}
