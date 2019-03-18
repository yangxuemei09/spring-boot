package com.tangdi.pump.model.form.adminUser;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminUserIdForm {
    /**
     * 操作者id
     */
    @NotBlank(message = "用户id为必填")
    private String userId;
}
