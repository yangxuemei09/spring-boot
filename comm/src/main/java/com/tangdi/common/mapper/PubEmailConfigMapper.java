package com.tangdi.common.mapper;

import com.tangdi.common.model.po.PubEmailConfig;
import java.util.List;

public interface PubEmailConfigMapper {
    int deleteByPrimaryKey(String emailConfigId);

    int insert(PubEmailConfig record);

    PubEmailConfig selectByPrimaryKey(String emailConfigId);

    List<PubEmailConfig> selectAll();

    int updateByPrimaryKey(PubEmailConfig record);
}