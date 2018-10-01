package com.maruf.i18n.security.service;

import com.maruf.i18n.security.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User signUp(User user);
}
