package com.tangdi.pump.model.form.adminRole;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminRoleIdForm {
    /**
     * 角色id
     */
    @NotBlank(message = "角色id为必填")
    private String roleId;
}
