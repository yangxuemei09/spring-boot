package com.tangdi.pump.mapper;

import com.tangdi.pump.model.form.adminMenu.AdminMenuListForm;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleForm;
import com.tangdi.pump.model.po.AdminMenu;
import java.util.List;

public interface AdminMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(String menuId);

    List<AdminMenu> selectAll();

    int updateByPrimaryKey(AdminMenu record);

    List<AdminMenu> selectListByRoleId(AdminMenuRoleForm adminMenuRoleForm);

    List<AdminMenu> selectPageByCondition(AdminMenuListForm adminMenuListForm);

    List<AdminMenu> selectRepeatList(AdminMenuListForm adminMenuListForm);

    int getMaxSort(String parentMenuId);
}