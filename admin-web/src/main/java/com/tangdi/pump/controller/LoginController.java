package com.tangdi.pump.controller;

import com.tangdi.common.aop.log.Log;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.login.LoginForm;
import com.tangdi.pump.model.form.login.TokenForm;
import com.tangdi.pump.model.vo.LoginVO;
import com.tangdi.pump.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ron
 * @date 2018/12/26 9:33
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    /**
     * 登录
     * @param loginForm
     * @param bindingResult
     * @return
     * @throws TrafficCheckedException
     */
    @Log("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult) throws TrafficCheckedException {
        LoginVO loginVO=loginService.login(loginForm);
        return ApiResponse.success(loginVO);
    }

    /**
     * 根据Token获取用户信息
     * @param tokenForm
     * @param bindingResult
     * @return
     * @throws TrafficCheckedException
     */
    @RequestMapping(value = "/getUserInfoByToken", method = RequestMethod.POST)
    public ApiResponse getUserInfoByToken(@Validated @RequestBody TokenForm tokenForm, BindingResult bindingResult) throws TrafficCheckedException {
        LoginVO loginVO=loginService.getUserInfoByToken(tokenForm.getToken());
        return ApiResponse.success(loginVO);
    }
}
