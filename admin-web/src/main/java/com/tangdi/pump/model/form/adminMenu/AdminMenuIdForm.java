package com.tangdi.pump.model.form.adminMenu;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminMenuIdForm {
    /**
     * 菜单id
     */
    @NotNull(message = "菜单id为必填")
    private String menuId;
}
