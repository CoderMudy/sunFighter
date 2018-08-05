package com.sun.fighter.exception;


/**
 * @创建人 chengyin
 * @创建时间 2018/7/24
 * @描述
 */
public class SunException extends RuntimeException {

    private Integer code;

    private String message;

    public SunException(SunExceptionEnum sunExceptionEnum) {
        this.code = sunExceptionEnum.getCode();
        this.message = sunExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
