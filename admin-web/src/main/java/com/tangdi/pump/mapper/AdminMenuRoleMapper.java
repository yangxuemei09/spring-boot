package com.tangdi.pump.mapper;

import com.tangdi.pump.model.po.AdminMenuRole;
import java.util.List;

public interface AdminMenuRoleMapper {
    int deleteByPrimaryKey(String roleMenuId);

    int insert(AdminMenuRole record);

    AdminMenuRole selectByPrimaryKey(String roleMenuId);

    List<AdminMenuRole> selectAll();

    int updateByPrimaryKey(AdminMenuRole record);

    void deleteByRoleId(String roleId);

    int insertList(List<AdminMenuRole> adminMenuRoleList);
}