package com.tangdi.pump.model.form.login;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author ron
 * @date 2018/12/26 11:05
 */
@Data
public class TokenForm {
    /**
     * token
     */
    @NotBlank(message = "token为必填")
    private String token;
}
