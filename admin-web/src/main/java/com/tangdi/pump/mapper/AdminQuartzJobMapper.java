package com.tangdi.pump.mapper;

import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobListForm;
import com.tangdi.pump.model.po.AdminQuartzJob;
import java.util.List;

public interface AdminQuartzJobMapper {
    int deleteByPrimaryKey(String quartzJobId);

    int insert(AdminQuartzJob record);

    AdminQuartzJob selectByPrimaryKey(String quartzJobId);

    List<AdminQuartzJob> selectAll();

    int updateByPrimaryKey(AdminQuartzJob record);

    List<AdminQuartzJob> selectListByCondition(AdminQuartzJobListForm adminQuartzJobListForm);
}