package com.tangdi.pump.model.form.adminMenuRole;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminMenuRoleForm {
    /**
     * 角色id
     */
    @NotBlank(message = "角色id为必填")
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 上级菜单id
     */
    private String parentMenuId;
    /**
     * 菜单显示名
     */
    private String name;
}
