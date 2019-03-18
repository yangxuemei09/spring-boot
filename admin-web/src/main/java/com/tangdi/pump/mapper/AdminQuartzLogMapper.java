package com.tangdi.pump.mapper;

import com.tangdi.pump.model.po.AdminQuartzLog;
import java.util.List;

public interface AdminQuartzLogMapper {
    int deleteByPrimaryKey(String quartzLogId);

    int insert(AdminQuartzLog record);

    AdminQuartzLog selectByPrimaryKey(String quartzLogId);

    List<AdminQuartzLog> selectAll();

    int updateByPrimaryKey(AdminQuartzLog record);
}