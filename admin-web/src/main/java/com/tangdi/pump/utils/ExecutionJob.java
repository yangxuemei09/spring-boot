package com.tangdi.pump.utils;

import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.utils.SpringContextHolder;
import com.tangdi.common.utils.ThrowableUtil;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminQuartzLogMapper;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobForm;
import com.tangdi.pump.model.po.AdminQuartzJob;
import com.tangdi.pump.model.po.AdminQuartzLog;
import com.tangdi.pump.service.AdminQuartzJobService;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 参考人人开源，https://gitee.com/renrenio/renren-security
 * @author
 * @date 2019-01-07
 */
@Async
public class ExecutionJob extends QuartzJobBean {

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExecutorService executorService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        AdminQuartzJob adminQuartzJob = (AdminQuartzJob) context.getMergedJobDataMap().get(AdminQuartzJob.JOB_KEY);
        // 获取spring bean
        AdminQuartzLogMapper adminQuartzLogMapper = SpringContextHolder.getBean("adminQuartzLogMapper");
        AdminQuartzJobService adminQuartzJobService = SpringContextHolder.getBean("adminQuartzJobService");
        QuartzManage quartzManage = SpringContextHolder.getBean("quartzManage");

        AdminQuartzLog log = new AdminQuartzLog();
        log.setJobName(adminQuartzJob.getJobName());
        log.setBaenName(adminQuartzJob.getBeanName());
        log.setMethodName(adminQuartzJob.getMethodName());
        log.setParams(adminQuartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(adminQuartzJob.getCronExpression());
        try {
            // 执行任务
            logger.info("任务准备执行，任务名称：{}", adminQuartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(adminQuartzJob.getBeanName(), adminQuartzJob.getMethodName(),
                    adminQuartzJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(String.valueOf(times));
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", adminQuartzJob.getJobName(), times);
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：{}" + adminQuartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            log.setTime(String.valueOf(times));
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            //出错就暂停任务
            quartzManage.pauseJob(adminQuartzJob);
            //更新状态
            AdminQuartzJobForm adminQuartzJobForm = new AdminQuartzJobForm();
            adminQuartzJobForm.setQuartzJobId(adminQuartzJob.getQuartzJobId());
            adminQuartzJobForm.setIsPause(PumpConstant.IS_BYTE_TRUE);
            try {
                adminQuartzJobService.editAdminQuartzJob(adminQuartzJobForm);
            } catch (TrafficCheckedException e1) {
                e1.printStackTrace();
            }
        } finally {
            adminQuartzLogMapper.insert(log);
        }
    }
}
