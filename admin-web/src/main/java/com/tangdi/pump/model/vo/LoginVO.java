package com.tangdi.pump.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginVO {
    /**
     * user-token
     */
    private String token;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 用户状态
     */
    private String userStatus;
    /**
     * 最后更新时间
     */
    private Date lastUpdPwdTime;

    private Date addTime;

    private Date updateTime;


}
