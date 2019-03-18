package com.tangdi.pump.model.form.adminQuartzJob;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminQuartzJobForm {
    /**
     * 定时任务id
     */
    private String quartzJobId;

    private String beanName;

    private String cronExpression;

    private Byte isPause;

    private String jobName;

    private String methodName;

    private String params;

    private String remark;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
