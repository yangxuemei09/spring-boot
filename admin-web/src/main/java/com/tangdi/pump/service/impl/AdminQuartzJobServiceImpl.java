package com.tangdi.pump.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminMenuRoleMapper;
import com.tangdi.pump.mapper.AdminQuartzJobMapper;
import com.tangdi.pump.mapper.AdminUserMapper;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobIdForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobListForm;
import com.tangdi.pump.model.form.adminUser.AdminUserListForm;
import com.tangdi.pump.model.po.AdminQuartzJob;
import com.tangdi.pump.model.vo.AdminUserVO;
import com.tangdi.pump.service.AdminQuartzJobService;
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
public class AdminQuartzJobServiceImpl implements AdminQuartzJobService {
    @Autowired
    private AdminQuartzJobMapper adminQuartzJobMapper;

    /**
     * 查询角色列表
     */
    @Override
    public PageInfo<AdminQuartzJob> getAdminQuartzJobList(AdminQuartzJobListForm adminQuartzJobListForm) throws TrafficCheckedException {
        //分页查询
        PageHelper.startPage(adminQuartzJobListForm.getPageNum(), adminQuartzJobListForm.getPageSize());
        List<AdminQuartzJob> list = adminQuartzJobMapper.selectListByCondition(adminQuartzJobListForm);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int addAdminQuartzJob(AdminQuartzJobForm adminQuartzJobForm) throws TrafficCheckedException {
        AdminQuartzJob record = BeanConvertor.convertor(adminQuartzJobForm, AdminQuartzJob.class);
        Date date = new Date();
        record.setQuartzJobId(IdGenUtils.getUUID());
        record.setIsPause(PumpConstant.IS_BYTE_TRUE);
        record.setUpdateTime(date);
        int num = adminQuartzJobMapper.insert(record);
        return num;
    }

    @Override
    public int editAdminQuartzJob(AdminQuartzJobForm adminQuartzJobForm) throws TrafficCheckedException {
        AdminQuartzJob record = BeanConvertor.convertor(adminQuartzJobForm, AdminQuartzJob.class);
        record.setUpdateTime(new Date());
        int num = adminQuartzJobMapper.updateByPrimaryKey(record);
        return num;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteAdminQuartzJob(AdminQuartzJobIdForm adminQuartzJobIdForm) throws TrafficCheckedException {
        int num = adminQuartzJobMapper.deleteByPrimaryKey(adminQuartzJobIdForm.getQuartzJobId());
        return num;
    }
}
