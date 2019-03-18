package com.tangdi.pump.service;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobIdForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobListForm;
import com.tangdi.pump.model.po.AdminQuartzJob;

public interface AdminQuartzJobService {

    /**
     * 查询定时任务列表
     */
    PageInfo<AdminQuartzJob> getAdminQuartzJobList(AdminQuartzJobListForm adminQuartzJobListForm) throws TrafficCheckedException;

    /**
     * 新增定时任务
     */
    int addAdminQuartzJob(AdminQuartzJobForm adminQuartzJobForm) throws TrafficCheckedException;

    /**
     * 修改定时任务
     */
    int editAdminQuartzJob(AdminQuartzJobForm adminQuartzJobForm) throws TrafficCheckedException;
    /**
     * 删除定时任务
     */
    int deleteAdminQuartzJob(AdminQuartzJobIdForm adminQuartzJobIdForm) throws TrafficCheckedException;
}
