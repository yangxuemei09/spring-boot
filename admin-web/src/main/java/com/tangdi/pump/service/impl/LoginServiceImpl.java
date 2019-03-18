package com.tangdi.pump.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.common.RedisKey;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.service.RedisService;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.pump.mapper.AdminUserMapper;
import com.tangdi.pump.model.form.login.LoginForm;
import com.tangdi.pump.model.po.AdminUser;
import com.tangdi.pump.model.vo.LoginVO;
import com.tangdi.pump.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 登录
     */
    @Override
    public LoginVO login(LoginForm loginForm) throws TrafficCheckedException {
        LoginVO loginVO=new LoginVO();
        AdminUser user = BeanConvertor.convertor(loginForm, AdminUser.class);
        user=adminUserMapper.login(user);
        if(user==null){
            throw new TrafficCheckedException(CodeEnum.ERROR_LOGIN);
        }
        //生成userToken
        String userToken= IdGenUtils.getUUID();

        //存储到redis
        String json= JSONObject.toJSONString(user);
        redisService.setByCompress(userToken, RedisKey.seconds,json);
        loginVO = BeanConvertor.convertor(user, LoginVO.class);
        loginVO.setToken(userToken);
        return loginVO;
    }

    /**
     * 根据Token获取用户信息
     */
    @Override
    public LoginVO getUserInfoByToken(String token) throws TrafficCheckedException {
        String userInfo = redisService.getByDecompress(token);
        LoginVO loginVO = JSON.parseObject(userInfo,LoginVO.class);
        return loginVO;
    }


}
