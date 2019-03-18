package com.tangdi.common.mapper;


import com.tangdi.common.model.po.PubPicture;

import java.util.List;

public interface PubPictureMapper {
    int deleteByPrimaryKey(String pictureId);

    int insert(PubPicture record);

    PubPicture selectByPrimaryKey(String pictureId);

    List<PubPicture> selectAll();

    int updateByPrimaryKey(PubPicture record);
}