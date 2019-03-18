package com.tangdi.pump.model.form.adminMenu;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminMenuForm {
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 上级菜单id
     */
    @NotNull(message = "上级菜单id为必填")
    private String parentMenuId;
    /**
     * 菜单显示名
     */
    @NotBlank(message = "菜单名为必填")
    private String name;
    /**
     * url
     */
    @NotBlank(message = "链接为必填")
    private String url;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单英文名
     */
     private String nameEn;
    /**
     * 路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否叶子节点
     */
    private Byte leaf;
    /**
     * 状态
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
