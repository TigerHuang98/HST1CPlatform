package com.anonymous.HST1C.data;

import com.anonymous.HST1C.User;

public interface UserRepository {
    long addUser(User user);
    User findUser(long id);
}
