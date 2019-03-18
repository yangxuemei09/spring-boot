package com.tangdi.pump.service;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminMenu.AdminMenuForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuIdForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuListForm;
import com.tangdi.pump.model.po.AdminMenu;
import com.tangdi.pump.model.vo.AdminMenuVO;

public interface AdminMenuService {

    /**
     * 查询菜单列表
     */
    PageInfo<AdminMenuVO> getMenusTree(AdminMenuListForm adminMenuListForm) throws TrafficCheckedException;

    /**
     * 新增菜单
     */
    int addAdminMenu(AdminMenuForm adminMenuForm) throws TrafficCheckedException;

    /**
     * 修改菜单
     */
    int editAdminMenu(AdminMenuForm adminMenuForm) throws TrafficCheckedException;
    /**
     * 删除菜单
     */
    int deleteAdminMenu(AdminMenuIdForm adminMenuIdForm) throws TrafficCheckedException;
    /**
     * 根据id查询菜单信息
     */
    AdminMenu getAdminMenuById(AdminMenuIdForm adminMenuIdForm) throws TrafficCheckedException;
}
