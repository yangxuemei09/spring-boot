package com.tangdi.pump.model.form.adminMenu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminMenuListForm {
    @NotNull(message = "当前页为必填")
    private Integer pageNum;

    @NotNull(message = "每页数量为必填")
    private Integer pageSize;

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
    /**
     * 菜单英文名
     */
    private String nameEn;
}
