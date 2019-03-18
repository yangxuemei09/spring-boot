package com.tangdi.pump.service;



import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleAddForm;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleForm;
import com.tangdi.pump.model.vo.AdminMenuRoleVO;

import java.util.List;

public interface AdminMenuRoleService {
    /**
     * 分配菜单
     */
    int assignAdminMenu(AdminMenuRoleAddForm adminRoleMenuAddForm) throws TrafficCheckedException;
    /**
     * 根据角色获取菜单树
     */
    List<AdminMenuRoleVO> getMenuListByRole(AdminMenuRoleForm adminRoleMenuForm) throws TrafficCheckedException;

}
