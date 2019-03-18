package com.tangdi.pump.model.form.adminRole;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminRoleForm {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名为必填")
    private String name;
    /**
     * 状态（01：启用，02：禁用）
     */
    private String isUse;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
