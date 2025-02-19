package com.abha.aums.users.controllers;

import com.abha.aums.users.services.UserService;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import com.abha.sharedlibrary.shared.constants.AppConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/verify-email/{token}")
  public ResponseEntity<CommonResponse> verifyEmail(@PathVariable String token) {
    boolean isVerified = userService.verifyUser(token);
    if (isVerified) {
      return ResponseEntity.ok(new CommonResponse(AppConstant.USER_VERIFIED));
    }
    return ResponseEntity.badRequest()
        .body(new CommonResponse(AppConstant.NOT_VERIFICATION));
  }
}
