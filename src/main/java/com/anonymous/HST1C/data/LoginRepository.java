package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Login;

public interface LoginRepository {
    int addLogin(Login login);
    Login findLogin(int id);
}
