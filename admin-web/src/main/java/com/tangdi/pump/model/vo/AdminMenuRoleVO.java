package com.tangdi.pump.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 14:01
 */
@Data
public class AdminMenuRoleVO {
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
     * 子菜单
     */
    private List<AdminMenuRoleVO> children;

    private String redirect;

    private AdminMenuMetaVo meta;

}
