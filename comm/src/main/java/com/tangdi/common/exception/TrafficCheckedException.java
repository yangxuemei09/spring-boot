package com.tangdi.common.exception;


import com.tangdi.common.common.CodeEnum;

/**
 * @author ron
 * @date 2018/12/26 18:14
 */
public class TrafficCheckedException extends Exception{
    private static final long serialVersionUID = -601551741978392427L;

    private Integer code;

    private String msg;

    public TrafficCheckedException() {
        super(CodeEnum.SYS_BUSY.getCode() + " : " + CodeEnum.SYS_BUSY.getMessage());

        this.code = CodeEnum.SYS_BUSY.getCode();
        this.msg = CodeEnum.SYS_BUSY.getMessage();
    }

    public TrafficCheckedException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TrafficCheckedException(CodeEnum codeEnum) {
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
