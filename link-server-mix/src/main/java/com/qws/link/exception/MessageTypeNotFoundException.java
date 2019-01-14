package com.qws.link.exception;

/**
 * @author qiwenshuai
 * @note 自定义异常-- 包头类型么有发现.
 * @since 19-1-14 15:37 by jdk 1.8
 */
public class MessageTypeNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 5282738035401297255L;

    private Integer code;//自定义异常码


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public MessageTypeNotFoundException(String message) {
        super(message);
    }

    public MessageTypeNotFoundException(String message, Integer code) {
        super(message);// 父类的构造函数；调用底层的Throwable的构造函数，将参数message赋值到detailMessage (Throwable的属性)
        this.code = code;//赋值code码
    }

}
