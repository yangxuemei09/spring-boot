package com.tangdi.pump.model.form.adminUser;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminUserListForm {
    @NotNull(message = "当前页为必填")
    private Integer pageNum;

    @NotNull(message = "每页数量为必填")
    private Integer pageSize;

    /**
     * 操作者id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 角色id
     */
    private String roleId;
}
