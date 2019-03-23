package com.anonymous.HST1C.data;

import com.anonymous.HST1C.User;

public interface UserRepository {
    User findUser(long id);
    User addUser(User user);
}
