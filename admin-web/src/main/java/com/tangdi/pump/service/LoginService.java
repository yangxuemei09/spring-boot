package com.tangdi.pump.service;


import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.login.LoginForm;
import com.tangdi.pump.model.vo.LoginVO;

public interface LoginService {

    /**
     * 登录
     */
    LoginVO login(LoginForm loginForm) throws TrafficCheckedException;

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    LoginVO getUserInfoByToken(String token) throws TrafficCheckedException;
}
