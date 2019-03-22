package com.maruf.localization.security.service;

import com.maruf.localization.security.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User signUp(User user);
}
