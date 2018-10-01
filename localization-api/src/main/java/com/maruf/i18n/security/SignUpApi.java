package com.maruf.i18n.security;

import com.maruf.i18n.security.entity.User;
import com.maruf.i18n.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/sign-up")
public class SignUpApi {

    private UserService userService;
    public SignUpApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signUp(user));
    }
}
