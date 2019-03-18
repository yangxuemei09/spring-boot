package com.tangdi.pump.mapper;

import com.tangdi.pump.model.form.adminUser.AdminUserListForm;
import com.tangdi.pump.model.po.AdminUser;
import com.tangdi.pump.model.vo.AdminUserVO;

import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(AdminUser record);

    AdminUser selectByPrimaryKey(String userId);

    List<AdminUser> selectAll();

    int updateByPrimaryKey(AdminUser record);


    AdminUser login(AdminUser user);

    List<AdminUserVO> selectListByCondition(AdminUserListForm adminUserListForm);

    List<AdminUser> selectRepeatList(AdminUserListForm adminUserListForm);

    int updatePasswordByUserId(AdminUser user);

    AdminUserVO selectById(String userId);
}