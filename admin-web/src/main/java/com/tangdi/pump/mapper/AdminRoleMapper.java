package com.tangdi.pump.mapper;

import com.tangdi.pump.model.form.adminRole.AdminRoleListForm;
import com.tangdi.pump.model.po.AdminRole;
import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(String roleId);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);

    List<AdminRole> selectRepeatList(AdminRoleListForm adminRoleListForm);

    List<AdminRole> selectAll1();
}