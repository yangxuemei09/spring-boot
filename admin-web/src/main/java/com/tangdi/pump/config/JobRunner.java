package com.tangdi.pump.config;

import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminQuartzJobMapper;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobListForm;
import com.tangdi.pump.model.po.AdminQuartzJob;
import com.tangdi.pump.utils.QuartzManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jie
 * @date 2019-01-07
 */
@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private AdminQuartzJobMapper adminQuartzJobMapper;
    @Autowired
    private QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        System.out.println("--------------------注入定时任务---------------------");
        AdminQuartzJobListForm adminQuartzJobListForm = new AdminQuartzJobListForm();
        adminQuartzJobListForm.setIsPause(PumpConstant.IS_BYTE_FALSE);
        List<AdminQuartzJob> quartzJobs = adminQuartzJobMapper.selectListByCondition(adminQuartzJobListForm);
        quartzJobs.forEach(quartzJob -> {
            quartzManage.addJob(quartzJob);
        });
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}
