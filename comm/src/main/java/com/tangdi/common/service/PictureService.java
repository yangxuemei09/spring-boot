package com.tangdi.common.service;

import com.tangdi.common.model.po.PubPicture;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jie
 * @date 2018-12-27
 */
public interface PictureService {

    /**
     * 上传图片
     * @param file
     * @param username
     * @return
     */
    PubPicture upload(MultipartFile file, String username);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    PubPicture findById(String id);

    /**
     * 删除图片
     * @param picture
     */
    void delete(PubPicture picture);
}
