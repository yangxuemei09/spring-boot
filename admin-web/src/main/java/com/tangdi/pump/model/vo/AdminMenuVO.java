package com.tangdi.pump.model.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 14:01
 */
@Data
public class AdminMenuVO {
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 菜单显示名
     */
    private String name;
    /**
     * 菜单英文名
     */
    private String nameEn;
    /**
     * 组件
     */
    private String component;
    /**
     * 路径
     */
    private String path;
    /**
     * 上级菜单id
     */
    private String parentMenuId;
    /**
     * 排序
     */
    private Integer sort;
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

    private Date addTime;

    private Date updateTime;
    /**
     * 子菜单
     */
    private List<AdminMenuVO> children;

}
