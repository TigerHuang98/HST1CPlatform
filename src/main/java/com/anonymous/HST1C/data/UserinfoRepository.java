package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Userinfo;

public interface UserinfoRepository {
    Userinfo addUserinfo(Userinfo userinfo);
    Userinfo findUserinfo(String username);
}
