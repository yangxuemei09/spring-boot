package com.tangdi.pump.controller;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.aop.log.Log;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminRole.AdminRoleForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleIdForm;
import com.tangdi.pump.model.form.adminRole.AdminRoleListForm;
import com.tangdi.pump.model.po.AdminRole;
import com.tangdi.pump.service.AdminRoleService;
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
@RequestMapping("/adminRole")
public class AdminRoleController {
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * @api {POST} /adminRole/getAdminRoleList 查询角色列表
     * @apiName getAdminRoleList
     * @apiGroup 角色
     * @apiDescription 查询角色列表
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} pageNum 当前页（必传）
     * @apiParam {Number} pageSize 每页数量（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 返回消息
     * @apiSuccess {Object} data 业务数据
     * @apiSuccess {Number} data.pageNum 当前页
     * @apiSuccess {Number} data.pageSize 每页数量
     * @apiSuccess {Number} data.pages 总共多少页
     * @apiSuccess {Number} data.total 总记录数
     * @apiSuccess {List} data.list 列表
     * @apiSuccess {Number} data.list.roleId 角色id
     * @apiSuccess {String} data.list.name 角色名
     * @apiSuccess {Number} data.list.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.list.updateTime 更新时间（时间戳）
     */
    @Log("查询角色列表")
    @RequestMapping(value = "getAdminRoleList", method = RequestMethod.POST)
    public ApiResponse getAdminRoleList(@Validated @RequestBody AdminRoleListForm adminRoleListForm, BindingResult bindingResult) throws TrafficCheckedException {
        PageInfo<AdminRole> page = adminRoleService.getAdminRoleList(adminRoleListForm);
        return ApiResponse.success(page);
    }

    /**
     * @api {POST} /adminRole/addAdminRole 新增角色信息
     * @apiName addAdminRole
     * @apiGroup 角色
     * @apiDescription 新增角色信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} name 角色名（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "addAdminRole",method= RequestMethod.POST)
    public ApiResponse addAdminRole(@Validated @RequestBody AdminRoleForm adminRoleForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminRoleService.addAdminRole(adminRoleForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminRole/editAdminRole 修改角色信息
     * @apiName editAdminRole
     * @apiGroup 角色
     * @apiDescription 修改角色信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} roleId 角色id（必传）
     * @apiParam {String} name 角色名（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "editAdminRole",method= RequestMethod.POST)
    public ApiResponse editAdminRole(@Validated @RequestBody AdminRoleForm adminRoleForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminRoleService.editAdminRole(adminRoleForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminRole/deleteAdminRole 删除角色信息
     * @apiName deleteAdminRole
     * @apiGroup 角色
     * @apiDescription 删除角色信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} roleId 角色id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "deleteAdminRole",method= RequestMethod.POST)
    public ApiResponse deleteAdminRole(@Validated @RequestBody AdminRoleIdForm adminRoleIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminRoleService.deleteAdminRole(adminRoleIdForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }
    /**
     * @api {POST} /adminRole/getAdminRoleById 根据id获取角色信息
     * @apiName getAdminRoleById
     * @apiGroup 角色
     * @apiDescription 根据id获取角色信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} roleId 角色id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 返回消息
     * @apiSuccess {Object} data 业务数据
     * @apiSuccess {Number} data.roleId 角色id
     * @apiSuccess {String} data.name 角色名
     * @apiSuccess {Number} data.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.updateTime 更新时间（时间戳）
     */
    @RequestMapping(value = "getAdminRoleById", method = RequestMethod.POST)
    public ApiResponse getAdminRoleById(@Validated @RequestBody AdminRoleIdForm adminRoleIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        AdminRole data = adminRoleService.getAdminRoleById(adminRoleIdForm);
        return ApiResponse.success(data);
    }

}
