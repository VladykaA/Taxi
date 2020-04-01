package com.vladyka.taxi.dto;

import com.vladyka.taxi.model.User;

import java.math.BigDecimal;

public class UserDTO {

    private String userName;

    private BigDecimal fund;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.fund = user.getFund();
    }

    @Override
    public String toString() {
        return "Name='" + userName + "'" +
                ", fund=" + fund;
    }
}
