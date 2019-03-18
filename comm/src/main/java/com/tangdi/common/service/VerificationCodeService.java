package com.tangdi.common.service;

import com.tangdi.common.model.po.PubVerifyCode;
import com.tangdi.common.model.vo.EmailVo;

/**
 * @author jie
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(PubVerifyCode code);

    /**
     * 验证
     * @param code
     */
    void validated(PubVerifyCode code);
}
