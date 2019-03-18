package com.tangdi.common.mapper;


import com.tangdi.common.model.po.PubVerifyCode;

import java.util.List;
import java.util.Map;

public interface PubVerifyCodeMapper {
    int deleteByPrimaryKey(String verifyCodeId);

    int insert(PubVerifyCode record);

    PubVerifyCode selectByPrimaryKey(String verifyCodeId);

    List<PubVerifyCode> selectAll();

    int updateByPrimaryKey(PubVerifyCode record);

    PubVerifyCode findByCondition(Map<String, Object> qryMap);
}