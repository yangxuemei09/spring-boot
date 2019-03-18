package com.tangdi.pump.controller;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.model.po.PubPicture;
import com.tangdi.common.service.PictureService;
import com.tangdi.pump.model.form.adminUser.*;
import com.tangdi.pump.model.po.AdminUser;
import com.tangdi.pump.model.vo.AdminUserVO;
import com.tangdi.pump.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ron
 * @date 2018/12/26 9:33
 */
@Slf4j
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PictureService pictureService;

    /**
     * @api {POST} /adminUser/getAdminUserList 查询用户列表
     * @apiName getAdminUserList
     * @apiGroup 用户
     * @apiDescription 查询用户列表
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} pageNum 当前页（必传）
     * @apiParam {Number} pageSize 每页数量（必传）
     * @apiParam {String} userName 用户名
     * @apiParam {String} mobile 手机
     * @apiParam {String} nickname 昵称
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
     * @apiSuccess {Number} data.list.userId 后台用户id
     * @apiSuccess {String} data.list.userName 用户名
     * @apiSuccess {String} data.list.nickname 昵称
     * @apiSuccess {String} data.list.mobile 手机
     * @apiSuccess {Number} data.list.roleId 角色id
     * @apiSuccess {Number} data.list.roleName 角色名
     * @apiSuccess {Number} data.list.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.list.updateTime 更新时间（时间戳）
     */
    @RequestMapping(value = "getAdminUserList", method = RequestMethod.POST)
    public ApiResponse getAdminUserList(@Validated @RequestBody AdminUserListForm adminUserListForm, BindingResult bindingResult) throws TrafficCheckedException {
        PageInfo<AdminUser> page = adminUserService.getAdminUserList(adminUserListForm);
        return ApiResponse.success(page);
    }

    /**
     * @api {POST} /adminUser/addAdminUser 新增用户信息
     * @apiName addAdminUser
     * @apiGroup 用户
     * @apiDescription 新增用户信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} userName 用户名（必传）
     * @apiParam {String} password 密码（必传）
     * @apiParam {String} mobile 手机
     * @apiParam {String} nickname 昵称
     * @apiParam {Number} roleId 用户角色（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "addAdminUser",method= RequestMethod.POST)
    public ApiResponse addAdminUser(@Validated @RequestBody AdminUserForm adminUserForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminUserService.addAdminUser(adminUserForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminUser/editAdminUser 修改用户信息
     * @apiName editAdminUser
     * @apiGroup 用户
     * @apiDescription 修改用户信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} userId 后台用户id（必传）
     * @apiParam {String} userName 用户名（必传）
     * @apiParam {String} mobile 手机
     * @apiParam {String} nickname 昵称
     * @apiParam {Number} roleId 用户角色（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "editAdminUser",method= RequestMethod.POST)
    public ApiResponse editAdminUser(@Validated @RequestBody AdminUserForm adminUserForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminUserService.editAdminUser(adminUserForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminUser/deleteAdminUser 删除用户信息
     * @apiName deleteAdminUser
     * @apiGroup 用户
     * @apiDescription 删除用户信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} userId 后台用户id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "deleteAdminUser",method= RequestMethod.POST)
    public ApiResponse deleteAdminUser(@Validated @RequestBody AdminUserIdForm adminUserIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminUserService.deleteAdminUser(adminUserIdForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }
    /**
     * @api {POST} /adminUser/editAdminUserPassword 修改用户密码
     * @apiName editAdminUserPassword
     * @apiGroup 用户
     * @apiDescription 修改用户密码
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} userId 后台用户id（必传）
     * @apiParam {String} password 密码（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "editAdminUserPassword",method= RequestMethod.POST)
    public ApiResponse editAdminUserPassword(@Validated @RequestBody AdminUserPasswordForm adminUserPasswordForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminUserService.editAdminUserPassword(adminUserPasswordForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminUser/getAdminUserById 根据id获取用户信息
     * @apiName getAdminUserById
     * @apiGroup 用户
     * @apiDescription 根据id获取用户信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} userId 用户id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 返回消息
     * @apiSuccess {Object} data 业务数据
     * @apiSuccess {Number} data.userId 后台用户id
     * @apiSuccess {String} data.userName 用户名
     * @apiSuccess {String} data.nickname 昵称
     * @apiSuccess {String} data.mobile 手机
     * @apiSuccess {Number} data.roleId 角色id
     * @apiSuccess {Number} data.roleName 角色名
     * @apiSuccess {Number} data.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.updateTime 更新时间（时间戳）
     */
    @RequestMapping(value = "getAdminUserById", method = RequestMethod.POST)
    public ApiResponse getAdminUserById(@Validated @RequestBody AdminUserIdForm adminUserIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        AdminUserVO data = adminUserService.getAdminUserById(adminUserIdForm);
        return ApiResponse.success(data);
    }

    /**
     * 修改头像
     * @return
     */
    @RequestMapping(value = "updateAvatar", method = RequestMethod.POST)
    public ApiResponse updateAvatar(@Validated @RequestBody AdminUserForm adminUserForm, BindingResult bindingResult) throws TrafficCheckedException {
        PubPicture picture = pictureService.upload(adminUserForm.getFile(),adminUserForm.getUserName());
        adminUserForm.setAvatar(picture.getUrl());
        adminUserService.editAdminUser(adminUserForm);
        return ApiResponse.success();
    }

}
