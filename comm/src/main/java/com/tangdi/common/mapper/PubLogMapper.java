package com.tangdi.common.mapper;

import com.tangdi.common.model.po.PubLog;

import java.util.List;

public interface PubLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(PubLog record);

    PubLog selectByPrimaryKey(String logId);

    List<PubLog> selectAll();

    int updateByPrimaryKey(PubLog record);
}