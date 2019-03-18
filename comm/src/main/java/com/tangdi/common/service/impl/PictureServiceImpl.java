package com.tangdi.common.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficRuntimeException;
import com.tangdi.common.mapper.PubPictureMapper;
import com.tangdi.common.model.po.PubPicture;
import com.tangdi.common.service.PictureService;
import com.tangdi.common.utils.ElAdminConstant;
import com.tangdi.common.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author jie
 * @date 2018-12-27
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PubPictureMapper pubPictureMapper;

    public static final String SUCCESS = "success";

    public static final String CODE = "code";

    public static final String MSG = "msg";

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PubPicture upload(MultipartFile multipartFile, String username) {
        File file = FileUtil.toFile(multipartFile);
        //将参数合成一个请求
        RestTemplate rest = new RestTemplate();

        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("smfile", resource);

        //设置头部，必须
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(param,headers);
        ResponseEntity<String> responseEntity = rest.exchange(ElAdminConstant.Url.SM_MS_URL, HttpMethod.POST, httpEntity, String.class);

        JSONObject jsonObject = JSONUtil.parseObj(responseEntity.getBody());
        PubPicture picture = null;
        if(!jsonObject.get(CODE).toString().equals(SUCCESS)){
            throw new TrafficRuntimeException(CodeEnum.SYS_BUSY);
        }
        //转成实体类
        picture = JSON.parseObject(jsonObject.get("data").toString(), PubPicture.class);
        picture.setSize(FileUtil.getSize(Integer.valueOf(picture.getSize())));
        picture.setUsername(username);
        picture.setFilename(FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename())+FileUtil.getExtensionName(multipartFile.getOriginalFilename()));
        pubPictureMapper.insert(picture);
        //删除临时文件
        FileUtil.deleteFile(file);
        return picture;
    }

    @Override
    public PubPicture findById(String id) {
        return pubPictureMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(PubPicture picture) {
        pubPictureMapper.deleteByPrimaryKey(picture.getPictureId());
        RestTemplate rest = new RestTemplate();
        try {
            ResponseEntity<String> str = rest.getForEntity(picture.getDeleteUrl(), String.class);
            if(str.getStatusCode().is2xxSuccessful()){
                pubPictureMapper.deleteByPrimaryKey(picture.getPictureId());
            }
        //如果删除的地址出错，直接删除数据库数据
        } catch(Exception e){
            pubPictureMapper.deleteByPrimaryKey(picture.getPictureId());
        }

    }
}
