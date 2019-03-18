package com.tangdi.pump.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.common.utils.MD5Utils;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminUserMapper;
import com.tangdi.pump.model.form.adminUser.AdminUserForm;
import com.tangdi.pump.model.form.adminUser.AdminUserIdForm;
import com.tangdi.pump.model.form.adminUser.AdminUserListForm;
import com.tangdi.pump.model.form.adminUser.AdminUserPasswordForm;
import com.tangdi.pump.model.po.AdminUser;
import com.tangdi.pump.model.vo.AdminUserVO;
import com.tangdi.pump.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 11:02
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;


    /**
     * 查询用户列表
     */
    @Override
    public PageInfo<AdminUser> getAdminUserList(AdminUserListForm adminUserListForm) throws TrafficCheckedException {
        //分页查询
        PageHelper.startPage(adminUserListForm.getPageNum(), adminUserListForm.getPageSize());
        List<AdminUserVO> list = adminUserMapper.selectListByCondition(adminUserListForm);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int addAdminUser(AdminUserForm adminUserForm) throws TrafficCheckedException {
        AdminUserListForm adminUserListForm = new AdminUserListForm();
        adminUserListForm.setUserName(adminUserForm.getUserName());
        List<AdminUser> list = adminUserMapper.selectRepeatList(adminUserListForm);
        if(list!=null && list.size()>0){
            throw new TrafficCheckedException(CodeEnum.AMIN_USER_NAME);
        }
        adminUserListForm = new AdminUserListForm();
        adminUserListForm.setMobile(adminUserForm.getMobile());
        list = adminUserMapper.selectRepeatList(adminUserListForm);
        if(list!=null && list.size()>0){
            throw new TrafficCheckedException(CodeEnum.AMIN_USER_MOBILE);
        }
        AdminUser record = BeanConvertor.convertor(adminUserForm, AdminUser.class);
        record.setUserId(IdGenUtils.getUUID());
        record.setUserStatus(PumpConstant.IS_USE_TRUE);
        record.setPassword(MD5Utils.MD5Encrypt(PumpConstant.ADMIN_PASSWORD_INIT));
        Date date = new Date();
        record.setAddTime(date);
        record.setUpdateTime(date);
        int num = adminUserMapper.insert(record);
        return num;
    }

    @Override
    public int editAdminUser(AdminUserForm adminUserForm) throws TrafficCheckedException {
        AdminUserListForm adminUserListForm = new AdminUserListForm();
        adminUserListForm.setUserName(adminUserForm.getUserName());
        List<AdminUser> list = adminUserMapper.selectRepeatList(adminUserListForm);
        if(list!=null && list.size()>0 && !adminUserForm.getUserId().equals(list.get(0).getUserId())){
            throw new TrafficCheckedException(CodeEnum.AMIN_USER_NAME);
        }
        adminUserListForm = new AdminUserListForm();
        adminUserListForm.setMobile(adminUserForm.getMobile());
        list = adminUserMapper.selectRepeatList(adminUserListForm);
        if(list!=null && list.size()>0){
            throw new TrafficCheckedException(CodeEnum.AMIN_USER_MOBILE);
        }
        AdminUser record = BeanConvertor.convertor(adminUserForm, AdminUser.class);
        record.setUpdateTime(new Date());
        int num = adminUserMapper.updateByPrimaryKey(record);
        return num;
    }

    @Override
    public int deleteAdminUser(AdminUserIdForm adminUserIdForm) throws TrafficCheckedException {
        int num = adminUserMapper.deleteByPrimaryKey(adminUserIdForm.getUserId());
        return num;
    }

    @Override
    public int editAdminUserPassword(AdminUserPasswordForm adminUserPasswordForm) throws TrafficCheckedException {
        AdminUser user = adminUserMapper.selectByPrimaryKey(adminUserPasswordForm.getUserId());
        if(!user.getPassword().equals(adminUserPasswordForm.getOldPassword())){
            throw new TrafficCheckedException(CodeEnum.ERROR_LOGIN);
        }
        user.setPassword(adminUserPasswordForm.getPassword());
        user.setUpdateTime(new Date());
        int num = adminUserMapper.updatePasswordByUserId(user);
        return num;
    }

    @Override
    public AdminUserVO getAdminUserById(AdminUserIdForm adminUserIdForm) throws TrafficCheckedException {
        AdminUserVO record = adminUserMapper.selectById(adminUserIdForm.getUserId());
        return record;
    }
}
