package com.tangdi.pump.model.form.adminQuartzJob;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminQuartzJobListForm {
    @NotNull(message = "当前页为必填")
    private Integer pageNum;

    @NotNull(message = "每页数量为必填")
    private Integer pageSize;
    /**
     * Spring Bean名称
     */
    private String beanName;
    /**
     * 状态：1暂停、0启用
     */
    private Byte isPause;
}
