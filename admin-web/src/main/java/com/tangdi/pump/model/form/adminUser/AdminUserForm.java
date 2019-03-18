package com.tangdi.pump.model.form.adminUser;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminUserForm {
    /**
     * 操作者id
     */
    private String userId;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名为必填")
    private String userName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 角色id
     */
    @NotBlank(message = "角色为必填")
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

    private MultipartFile file;

}
