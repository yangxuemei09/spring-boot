package com.tangdi.pump.model.form.adminRole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminRoleListForm {
    @NotNull(message = "当前页为必填")
    private Integer pageNum;

    @NotNull(message = "每页数量为必填")
    private Integer pageSize;

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名
     */
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
