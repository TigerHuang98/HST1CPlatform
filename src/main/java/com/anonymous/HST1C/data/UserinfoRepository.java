package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Userinfo;

public interface UserinfoRepository {
    String addUser(Userinfo userinfo);
    Userinfo findUser(String username);
}
