package com.tangdi.common.common;

public enum CodeEnum {
    SUCCESS(1000, "处理成功"),
    SYS_BUSY(1001, "系统繁忙，请稍后再试"),
    INVALID_PARAM(1002, "请求参数有误"),
    NOT_LOGIN(1003, "请登录"),
    ERROR_LOGIN(1004, "用户名或密码错误"),
    CHANNEL_PRICE_NULL(1005, "渠道价格cpa,cps,cpi不能同时为空"),
    CHANNEL_PRICE_TIME_ERR(1006, "渠道价格设置时间重复"),
    PRODUCT_PRICE_NULL(1007, "产品价格cpa,cps,cpi不能同时为空"),
    PRODUCT_PRICE_TIME_ERR(1008, "产品价格设置时间重复"),
    AMIN_USER_NAME(1009, "用户名重复"),
    AMIN_MENU_NAME(1010, "菜单名重复"),
    AMIN_ROLE_NAME(1011, "角色名重复"),
    CHANNEL_NAME(1012, "渠道名重复"),
    CHANNEL_FR(1013, "渠道代码重复"),
    PRODUCT_NAME(1014, "产品名重复"),
    AMIN_USER_MOBILE(1015, "手机号重复"),
    PRODUCT_GROUP_DELETE(1016, "产品组存在产品，删除失败"),
    ADMIN_ROLE_DELETE(1017, "角色存在用户，删除失败"),
    PRODUCT_GROUP_ADD(1018, "该流量来源已关联产品组");


    private Integer code;

    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * @api code 状态码
     * @apiName code
     * @apiGroup 公共模块
     * @apiDescription 状态码
     *
     * @apiSuccess {Number} 1000 处理成功
     * @apiSuccess {Number} 1001 系统繁忙，请稍后再试
     * @apiSuccess {Number} 1002 请求参数有误
     * @apiSuccess {Number} 1003 请登录
     * @apiSuccess {Number} 1004 用户名或密码错误
     * @apiSuccess {Number} 1005 渠道价格cpa,cps,cpi不能同时为空
     * @apiSuccess {Number} 1006 渠道价格设置时间重复
     * @apiSuccess {Number} 1007 产品价格cpa,cps,cpi不能同时为空
     * @apiSuccess {Number} 1008 产品价格设置时间重复
     * @apiSuccess {Number} 1009 用户名重复
     * @apiSuccess {Number} 1010 菜单名重复
     * @apiSuccess {Number} 1011 角色名重复
     * @apiSuccess {Number} 1012 渠道名重复
     * @apiSuccess {Number} 1013 渠道代码重复
     * @apiSuccess {Number} 1014 产品名重复
     * @apiSuccess {Number} 1015 手机号重复
     * @apiSuccess {Number} 1016 产品组存在产品，删除失败
     * @apiSuccess {Number} 1017 角色存在用户，删除失败
     * @apiSuccess {Number} 1018 该流量来源已关联产品组
     */
}
