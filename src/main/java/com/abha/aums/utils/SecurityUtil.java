package com.abha.aums.utils;

import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {
  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String hashPassword(String password) {
    return encoder.encode(password);
  }

  public static boolean verifyPassword(String rawPassword, String hashedPassword) {
    return encoder.matches(rawPassword, hashedPassword);
  }

  public static String generateSecureToken() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] bytes = new byte[32];
    secureRandom.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }
}
