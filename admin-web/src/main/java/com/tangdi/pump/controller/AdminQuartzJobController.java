package com.tangdi.pump.controller;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobIdForm;
import com.tangdi.pump.model.form.adminQuartzJob.AdminQuartzJobListForm;
import com.tangdi.pump.model.po.AdminQuartzJob;
import com.tangdi.pump.service.AdminQuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ron
 * @date 2018/12/26 9:33
 */
@Slf4j
@RestController
@RequestMapping("/adminQuartzJob")
public class AdminQuartzJobController {
    @Autowired
    private AdminQuartzJobService adminQuartzJobService;

    /**
     * 查询列表
     */
    @RequestMapping(value = "getAdminQuartzJobList", method = RequestMethod.POST)
    public ApiResponse getAdminQuartzJobList(@Validated @RequestBody AdminQuartzJobListForm adminQuartzJobListForm, BindingResult bindingResult) throws TrafficCheckedException {
        PageInfo<AdminQuartzJob> page = adminQuartzJobService.getAdminQuartzJobList(adminQuartzJobListForm);
        return ApiResponse.success(page);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "addAdminQuartzJob",method= RequestMethod.POST)
    public ApiResponse addAdminQuartzJob(@Validated @RequestBody AdminQuartzJobForm adminQuartzJobForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminQuartzJobService.addAdminQuartzJob(adminQuartzJobForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "editAdminQuartzJob",method= RequestMethod.POST)
    public ApiResponse editAdminQuartzJob(@Validated @RequestBody AdminQuartzJobForm adminQuartzJobForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminQuartzJobService.editAdminQuartzJob(adminQuartzJobForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * 删除
     */
    @RequestMapping(value = "deleteAdminQuartzJob",method= RequestMethod.POST)
    public ApiResponse deleteAdminQuartzJob(@Validated @RequestBody AdminQuartzJobIdForm adminQuartzJobIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminQuartzJobService.deleteAdminQuartzJob(adminQuartzJobIdForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

}
