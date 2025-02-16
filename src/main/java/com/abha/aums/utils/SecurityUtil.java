package com.abha.aums.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {
  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String hashPassword(String password) {
    return encoder.encode(password);
  }

  public static boolean verifyPassword(String rawPassword, String hashedPassword) {
    return encoder.matches(rawPassword, hashedPassword);
  }
}
