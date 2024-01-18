package com.nisum.msusuario.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.stream.Collectors;

@Builder
@Data
public class ApiResponse implements Serializable {

    private String message;

    private Object response;

    public static User mutate(User user) {

        return User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password("*******")
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getIsActive())
                .phones(user.getPhones().stream()
                        .map(phone -> Phone.builder()
                                    .number(phone.getNumber())
                                    .citycode(phone.getCitycode())
                                    .countrycode(phone.getCountrycode())
                                    .build())
                        .collect(Collectors.toList()))
                .build();
    }



}
