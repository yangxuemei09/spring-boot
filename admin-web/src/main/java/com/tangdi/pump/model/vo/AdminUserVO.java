package com.tangdi.pump.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 14:01
 */
@Data
public class AdminUserVO {
    /**
     * 操作者id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态
     */
    private String userStatus;
    /**
     * 最后更新时间
     */
    private Date lastUpdPwdTime;

}
