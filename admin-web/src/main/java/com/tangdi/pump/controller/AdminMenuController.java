package com.tangdi.pump.controller;

import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.ApiResponse;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.model.form.adminMenu.AdminMenuForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuIdForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuListForm;
import com.tangdi.pump.model.po.AdminMenu;
import com.tangdi.pump.model.vo.AdminMenuVO;
import com.tangdi.pump.service.AdminMenuService;
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
@RequestMapping("/adminMenu")
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * @api {POST} /adminMenu/getAdminMenuList 获取菜单树
     * @apiName getAdminMenuList
     * @apiGroup 菜单
     * @apiDescription 获取菜单树
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} pageNum 当前页（必传）
     * @apiParam {Number} pageSize 每页数量（必传）
     * @apiParam {String} name 菜单名
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
     * @apiSuccess {Number} data.list.menuId 菜单id
     * @apiSuccess {String} data.list.parentMenuId 上级菜单id
     * @apiSuccess {String} data.list.name 菜单名
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
    @RequestMapping(value = "getMenusTree", method = RequestMethod.POST)
    public ApiResponse getMenusTree(@Validated @RequestBody AdminMenuListForm adminMenuListForm, BindingResult bindingResult) throws TrafficCheckedException {
        PageInfo<AdminMenuVO> page = adminMenuService.getMenusTree(adminMenuListForm);
        return ApiResponse.success(page);
    }

    /**
     * @api {POST} /adminMenu/addAdminMenu 新增菜单信息
     * @apiName addAdminMenu
     * @apiGroup 菜单
     * @apiDescription 新增菜单信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} parentMenuId 上级菜单id（如顶级菜单为0，必传）
     * @apiParam {String} name 菜单名（必传）
     * @apiParam {String} url 链接（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "addAdminMenu",method= RequestMethod.POST)
    public ApiResponse addAdminMenu(@Validated @RequestBody AdminMenuForm adminMenuForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminMenuService.addAdminMenu(adminMenuForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminMenu/editAdminMenu 修改菜单信息
     * @apiName editAdminMenu
     * @apiGroup 菜单
     * @apiDescription 修改菜单信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} menuId 菜单id（必传）
     * @apiParam {String} name 菜单名（必传）
     * @apiParam {String} url 链接（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "editAdminMenu",method= RequestMethod.POST)
    public ApiResponse editAdminMenu(@Validated @RequestBody AdminMenuForm adminMenuForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminMenuService.editAdminMenu(adminMenuForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }
    /**
     * @api {POST} /adminMenu/deleteAdminMenu 删除菜单信息
     * @apiName deleteAdminMenu
     * @apiGroup 菜单
     * @apiDescription 删除菜单信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {Number} menuId 菜单id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 提示信息
     */
    @RequestMapping(value = "deleteAdminMenu",method= RequestMethod.POST)
    public ApiResponse deleteAdminMenu(@Validated @RequestBody AdminMenuIdForm adminMenuIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        int num = adminMenuService.deleteAdminMenu(adminMenuIdForm);
        if(num==1){
            return ApiResponse.success();
        } else {
            return ApiResponse.fail(CodeEnum.SYS_BUSY);
        }
    }

    /**
     * @api {POST} /adminMenu/getAdminMenuById 根据id获取菜单信息
     * @apiName getAdminMenuById
     * @apiGroup 菜单
     * @apiDescription 根据id获取菜单信息
     *
     * @apiHeader {String} user-token  访问令牌.
     *
     * @apiParam {String} menuId 菜单id（必传）
     *
     * @apiSuccess {boolean} status  返回状态（true:表示无错误 false:有错误）
     * @apiSuccess {Number} code 返回状态码
     * @apiSuccess {String} message 返回消息
     * @apiSuccess {Object} data 业务数据
     * @apiSuccess {Number} data.menuId 菜单id
     * @apiSuccess {Number} data.parentMenuId 上级菜单id
     * @apiSuccess {String} data.name 菜单名
     * @apiSuccess {String} data.url 链接
     * @apiSuccess {Number} data.sort 排序
     * @apiSuccess {Number} data.addTime 添加时间（时间戳）
     * @apiSuccess {Number} data.updateTime 更新时间（时间戳）
     */
    @RequestMapping(value = "getAdminMenuById", method = RequestMethod.POST)
    public ApiResponse getAdminMenuById(@Validated @RequestBody AdminMenuIdForm adminMenuIdForm, BindingResult bindingResult) throws TrafficCheckedException {
        AdminMenu data = adminMenuService.getAdminMenuById(adminMenuIdForm);
        return ApiResponse.success(data);
    }

}
