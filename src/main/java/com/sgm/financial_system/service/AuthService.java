package com.sgm.financial_system.service;

import com.sgm.financial_system.dto.login.LoginRequestDto;

public interface AuthService {

    String authenticate( LoginRequestDto loginRequest);

}
