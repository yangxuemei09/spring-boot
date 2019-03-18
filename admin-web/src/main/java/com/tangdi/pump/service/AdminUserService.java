package com.tangdi.pump.service;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminUser.AdminUserForm;
import com.tangdi.pump.model.form.adminUser.AdminUserIdForm;
import com.tangdi.pump.model.form.adminUser.AdminUserListForm;
import com.tangdi.pump.model.form.adminUser.AdminUserPasswordForm;
import com.tangdi.pump.model.po.AdminUser;
import com.tangdi.pump.model.vo.AdminUserVO;

public interface AdminUserService {

    /**
     * 查询用户列表
     */
    PageInfo<AdminUser> getAdminUserList(AdminUserListForm adminUserListForm) throws TrafficCheckedException;

    /**
     * 新增用户
     */
    int addAdminUser(AdminUserForm adminUserForm) throws TrafficCheckedException;

    /**
     * 修改用户
     */
    int editAdminUser(AdminUserForm adminUserForm) throws TrafficCheckedException;
    /**
     * 删除用户
     */
    int deleteAdminUser(AdminUserIdForm adminUserIdForm) throws TrafficCheckedException;

    /**
     * 修改用户密码
     */
    int editAdminUserPassword(AdminUserPasswordForm adminUserPasswordForm) throws TrafficCheckedException;
    /**
     * 根据id查询用户信息
     */
    AdminUserVO getAdminUserById(AdminUserIdForm adminUserIdForm) throws TrafficCheckedException;
}
