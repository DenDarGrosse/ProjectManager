package ru.local.projectmanager.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderUtil {

    public static String encryptPassword(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
