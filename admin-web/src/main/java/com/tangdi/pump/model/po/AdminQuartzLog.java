package com.tangdi.pump.model.po;

import java.util.Date;

public class AdminQuartzLog {
    private String quartzLogId;

    private String baenName;

    private Date createTime;

    private String cronExpression;

    private Boolean isSuccess;

    private String jobName;

    private String methodName;

    private String params;

    private String time;

    private String exceptionDetail;

    public String getQuartzLogId() {
        return quartzLogId;
    }

    public void setQuartzLogId(String quartzLogId) {
        this.quartzLogId = quartzLogId == null ? null : quartzLogId.trim();
    }

    public String getBaenName() {
        return baenName;
    }

    public void setBaenName(String baenName) {
        this.baenName = baenName == null ? null : baenName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }
}