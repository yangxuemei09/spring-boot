package com.tangdi.pump.service;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminRole.AdminRoleForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleIdForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleListForm;
import com.tangdi.pump.model.po.AdminRole;

public interface AdminRoleService {

    /**
     * 查询角色列表
     */
    PageInfo<AdminRole> getAdminRoleList(AdminRoleListForm adminRoleListForm) throws TrafficCheckedException;

    /**
     * 新增角色
     */
    int addAdminRole(AdminRoleForm adminRoleForm) throws TrafficCheckedException;

    /**
     * 修改角色
     */
    int editAdminRole(AdminRoleForm adminRoleForm) throws TrafficCheckedException;
    /**
     * 删除角色
     */
    int deleteAdminRole(AdminRoleIdForm adminRoleIdForm) throws TrafficCheckedException;
    /**
     * 根据id查询角色信息
     */
    AdminRole getAdminRoleById(AdminRoleIdForm adminRoleIdForm) throws TrafficCheckedException;
}
