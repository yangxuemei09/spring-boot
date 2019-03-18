package com.tangdi.pump.model.form.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class LoginForm {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名为必填")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "密码为必填")
    private String password;
}
