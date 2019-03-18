package com.tangdi.common.exception;


import com.tangdi.common.common.CodeEnum;

/**
 * @author ron
 * @date 2018/12/26 18:20
 */
public class TrafficRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -4871589748638140533L;

    private Integer code;

    private String msg;

    public TrafficRuntimeException() {
        super(CodeEnum.SYS_BUSY.getCode() + " : " + CodeEnum.SYS_BUSY.getMessage());

        this.code = CodeEnum.SYS_BUSY.getCode();
        this.msg = CodeEnum.SYS_BUSY.getMessage();
    }

    public TrafficRuntimeException(Integer code, String msg) {
        super(code + " : " + msg);
        this.code = code;
        this.msg = msg;
    }

    public TrafficRuntimeException(CodeEnum codeEnum) {
        super(codeEnum.getCode() + " : " + codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
