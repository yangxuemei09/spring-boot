package com.tangdi.common.service;

import com.tangdi.common.model.po.PubEmailConfig;
import com.tangdi.common.model.vo.EmailVo;
import org.springframework.scheduling.annotation.Async;

/**
 * @author jie
 * @date 2018-12-26
 */
public interface EmailService {

    /**
     * 更新邮件配置
     * @param emailConfig
     * @param old
     * @return
     */
    PubEmailConfig update(PubEmailConfig emailConfig, PubEmailConfig old);

    /**
     * 查询配置
     * @return
     */
    PubEmailConfig find();

    /**
     * 发送邮件
     * @param emailVo
     * @param emailConfig
     * @throws Exception
     */
    @Async
    void send(EmailVo emailVo, PubEmailConfig emailConfig) throws Exception;
}
