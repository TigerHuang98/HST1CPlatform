package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Login;

public interface LoginRepository {
    int addCustomerLogin(Login login);
    Login findLoginById(int userid);
    Login findLoginByUsername(String username);
}
