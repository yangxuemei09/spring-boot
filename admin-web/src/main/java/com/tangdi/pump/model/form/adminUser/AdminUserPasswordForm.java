package com.tangdi.pump.model.form.adminUser;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminUserPasswordForm {
    /**
     * 操作者id
     */
    @NotBlank(message = "用户id为必填")
    private String userId;
    /**
     * 新密码
     */
    @NotBlank(message = "密码为必填")
    private String password;
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码为必填")
    private String oldPassword;


}
