package com.tangdi.pump.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminMenuRoleMapper;
import com.tangdi.pump.mapper.AdminRoleMapper;
import com.tangdi.pump.mapper.AdminUserMapper;
import com.tangdi.pump.model.form.adminRole.AdminRoleForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleIdForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleListForm;
import com.tangdi.pump.model.form.adminUser.AdminUserListForm;
import com.tangdi.pump.model.po.AdminRole;
import com.tangdi.pump.model.vo.AdminUserVO;
import com.tangdi.pump.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 11:02
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminMenuRoleMapper adminMenuRoleMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 查询角色列表
     */
    @Override
    public PageInfo<AdminRole> getAdminRoleList(AdminRoleListForm adminRoleListForm) throws TrafficCheckedException {
        //分页查询
        PageHelper.startPage(adminRoleListForm.getPageNum(), adminRoleListForm.getPageSize());
        List<AdminRole> list = adminRoleMapper.selectAll1();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int addAdminRole(AdminRoleForm adminRoleForm) throws TrafficCheckedException {
        AdminRoleListForm adminRoleListForm = new AdminRoleListForm();
        adminRoleListForm.setName(adminRoleForm.getName());
        List<AdminRole> list = adminRoleMapper.selectRepeatList(adminRoleListForm);
        if(list!=null && list.size()>0){
            throw new TrafficCheckedException(CodeEnum.AMIN_ROLE_NAME);
        }
        AdminRole record = BeanConvertor.convertor(adminRoleForm, AdminRole.class);
        Date date = new Date();
        record.setRoleId(IdGenUtils.getUUID());
        record.setIsUse(PumpConstant.IS_USE_TRUE);
        record.setAddTime(date);
        record.setUpdateTime(date);
        int num = adminRoleMapper.insert(record);
        return num;
    }

    @Override
    public int editAdminRole(AdminRoleForm adminRoleForm) throws TrafficCheckedException {
        AdminRoleListForm adminRoleListForm = new AdminRoleListForm();
        adminRoleListForm.setName(adminRoleForm.getName());
        List<AdminRole> list = adminRoleMapper.selectRepeatList(adminRoleListForm);
        if(list!=null && list.size()>0 && !adminRoleForm.getRoleId().equals(list.get(0).getRoleId())){
            throw new TrafficCheckedException(CodeEnum.AMIN_ROLE_NAME);
        }
        AdminRole record = BeanConvertor.convertor(adminRoleForm, AdminRole.class);
        record.setUpdateTime(new Date());
        int num = adminRoleMapper.updateByPrimaryKey(record);
        return num;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteAdminRole(AdminRoleIdForm adminRoleIdForm) throws TrafficCheckedException {
        // 检查该角色是否存在用户
        AdminUserListForm adminUserListForm = new AdminUserListForm();
        adminUserListForm.setRoleId(adminRoleIdForm.getRoleId());
        List<AdminUserVO> adminUserList = adminUserMapper.selectListByCondition(adminUserListForm);
        if(adminUserList!=null && adminUserList.size()>0){
            throw new TrafficCheckedException(CodeEnum.ADMIN_ROLE_DELETE);
        }
        // 删除角色
        int num = adminRoleMapper.deleteByPrimaryKey(adminRoleIdForm.getRoleId());
        // 删除该角色绑定的菜单数据
        adminMenuRoleMapper.deleteByRoleId(adminRoleIdForm.getRoleId());
        return num;
    }

    @Override
    public AdminRole getAdminRoleById(AdminRoleIdForm adminRoleIdForm) throws TrafficCheckedException {
        AdminRole record = adminRoleMapper.selectByPrimaryKey(adminRoleIdForm.getRoleId());
        return  record;
    }
}
