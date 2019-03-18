package com.tangdi.pump.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.common.Constants;
import com.tangdi.common.service.RedisService;
import com.tangdi.pump.model.po.AdminUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ron
 * @date 2018/12/28 10:24
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(Constants.TOKEN);
        if(StringUtils.isBlank(token)){
            failResponse(httpServletResponse);
            return false;
        }else{
            String json = redisService.getByDecompress(token);
            if(StringUtils.isBlank(json)){
                failResponse(httpServletResponse);
                return false;
            }
            AdminUser user = JSONObject.parseObject(json, AdminUser.class);
            log.debug("登录成功");
        }
        return true;
    }


    private void failResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        OutputStream out = httpServletResponse.getOutputStream();
        String result = JSONObject.toJSONString(ApiResponse.fail(CodeEnum.NOT_LOGIN), SerializerFeature.WriteMapNullValue);
        out.write(result.getBytes("UTF-8"));
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
