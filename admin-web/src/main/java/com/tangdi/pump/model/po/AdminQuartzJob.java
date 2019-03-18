package com.tangdi.pump.model.po;

import java.util.Date;

public class AdminQuartzJob {

    public static final String JOB_KEY = "JOB_KEY";

    private String quartzJobId;

    private String beanName;

    private String cronExpression;

    private Byte isPause;

    private String jobName;

    private String methodName;

    private String params;

    private String remark;

    private Date updateTime;

    public String getQuartzJobId() {
        return quartzJobId;
    }

    public void setQuartzJobId(String quartzJobId) {
        this.quartzJobId = quartzJobId == null ? null : quartzJobId.trim();
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public Byte getIsPause() {
        return isPause;
    }

    public void setIsPause(Byte isPause) {
        this.isPause = isPause;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}