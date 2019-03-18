package com.tangdi.pump.controller;

import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleAddForm;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleForm;
import com.tangdi.pump.model.vo.AdminMenuRoleVO;
import com.tangdi.pump.service.AdminMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 9:33
 */
@Slf4j
@RestController
@RequestMapping("/adminMenuRole")
public class AdminMenuRoleController {
    @Autowired
    private AdminMenuRoleService adminMenuRoleService;

    /**
     * @api {POST} /adminRole/assignAdminMenu 分配菜单
     * @apiName assignAdminMenu
     * @apiGroup 角色
     * @apiDescription 分配菜单
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} roleId 角色id（必传）
     * @apiParam {String} menuIds 菜单id集（菜单id为Number，多个菜单id中间用逗号分隔，必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "assignAdminMenu",method= RequestMethod.POST)
    public ApiResponse assignAdminMenu(@Validated @RequestBody AdminMenuRoleAddForm adminMenuRoleAddForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminMenuRoleService.assignAdminMenu(adminMenuRoleAddForm);
        if(num>0){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }
    /**
     * @api {POST} /adminRole/getMenuListByRole 根据角色获取菜单树
     * @apiName getMenuListByRole
     * @apiGroup 角色
     * @apiDescription 根据角色获取菜单树
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} roleId 角色id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     * @apiSuccess {Object} data 业务数据
     * @apiSuccess {Number} data.total 总记录数
     * @apiSuccess {List} data.list 列表
     * @apiSuccess {Number} data.list.menuId 菜单id
     * @apiSuccess {String} data.list.parentMenuId 上级菜单id
     * @apiSuccess {String} data.list.name 菜单名称
     * @apiSuccess {String} data.list.url 链接
     * @apiSuccess {Number} data.list.sort 排序
     * @apiSuccess {Number} data.list.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.list.updateTime 更新时间（时间戳）
     * @apiSuccess {Number} data.list.children 子菜单树
     * @apiSuccess {Number} data.list.children.menuId 菜单id
     * @apiSuccess {String} data.list.children.parentMenuId 上级菜单id
     * @apiSuccess {String} data.list.children.name 菜单名
     * @apiSuccess {String} data.list.children.url 链接
     * @apiSuccess {Number} data.list.children.sort 排序
     * @apiSuccess {Number} data.list.children.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.list.children.updateTime 更新时间（时间戳）
     */
    @RequestMapping(value = "getMenusTreeByRole",method= RequestMethod.POST)
    public ApiResponse getMenusTreeByRole(@Validated @RequestBody AdminMenuRoleForm adminMenuRoleForm, BindingResult bindingResult) throws TrafficCheckedException {
        List<AdminMenuRoleVO> menuByRoleList = adminMenuRoleService.getMenuListByRole(adminMenuRoleForm);
        return ApiResponse.success(menuByRoleList);
    }


}
