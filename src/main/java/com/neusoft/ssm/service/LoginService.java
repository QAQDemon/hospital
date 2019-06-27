package com.neusoft.ssm.service;

import com.neusoft.ssm.bean.User;

public interface LoginService {
    User checkLogin(User signUser);
}
