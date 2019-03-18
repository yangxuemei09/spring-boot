package com.tangdi.common.common;


import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.exception.TrafficRuntimeException;

/**
 * @author ron
 * @date 2018/12/25 18:32
 */
public class ApiResponse<T> {
    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回状态，true:表示无错误 false:有错误
     */
    private boolean status;

    /**
     * 业务数据
     */
    private T data;


    private ApiResponse() {
    }


    private ApiResponse(Integer code, String message, boolean status, T data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }


    public static ApiResponse success() {
        return new ApiResponse(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), true, null);
    }

    public static<T> ApiResponse<T> success(T data) {
        return new ApiResponse(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), true, data);
    }

    public static ApiResponse fail(CodeEnum codeEnum) {
        return new ApiResponse(codeEnum.getCode(), codeEnum.getMessage(), false, null);
    }

    public static<T> ApiResponse<T> fail(CodeEnum codeEnum, T data) {
        return new ApiResponse(codeEnum.getCode(), codeEnum.getMessage(), false, data);
    }

    public static ApiResponse fail(Integer code, String message) {
        return new ApiResponse(code, message, false, null);
    }

    public static ApiResponse buildErrorResult(Throwable exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(false);
        if (exception instanceof TrafficCheckedException) {
            apiResponse.setCode(((TrafficCheckedException) exception).getCode());
            apiResponse.setMessage(((TrafficCheckedException) exception).getMsg());
        } else if (exception instanceof TrafficRuntimeException) {
            apiResponse.setCode(((TrafficRuntimeException) exception).getCode());
            apiResponse.setMessage(((TrafficRuntimeException) exception).getMsg());
        } else {
            apiResponse.setCode(CodeEnum.SYS_BUSY.getCode());
            apiResponse.setMessage(CodeEnum.SYS_BUSY.getMessage());
        }
        return apiResponse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
