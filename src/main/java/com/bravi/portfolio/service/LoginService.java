package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.LoginRequest;
import com.bravi.portfolio.dto.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest loginRequest);
}
