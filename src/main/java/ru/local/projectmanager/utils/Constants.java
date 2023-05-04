package ru.local.projectmanager.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Constants {
    AUTHORIZATION("AUTHORIZATION"), BEARER("Bearer ");

    private final String constValue;
}
