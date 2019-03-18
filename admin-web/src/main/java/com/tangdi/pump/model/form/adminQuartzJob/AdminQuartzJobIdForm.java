package com.tangdi.pump.model.form.adminQuartzJob;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class AdminQuartzJobIdForm {
    /**
     * 角色id
     */
    @NotBlank(message = "定时任务id为必填")
    private String quartzJobId;
}
