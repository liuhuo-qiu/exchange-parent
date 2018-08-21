/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.result;

import java.io.Serializable;
/**  
  * 接口 默认返回对象
  * @author qlj  
  * @version $Id: BaseResult.java, v 0.1 2018-04-28 9:30 legend Exp $$ 
  */
public class BaseResult<T> implements Serializable {

    /**
     * 执行成功标志
     */
    private boolean success=false;

    /**.
     * 接口返回码
     */
    private String errorCode;

    /**
     * 接口返回的消息
     */
    private String message;

    /**
     * 接口返回的数据
     */
    private T data;


    /**
     * Getter method for property   success.
     *
     * @return property value of success
     */
    public boolean success() {
        return success;
    }

    /**
     * Setter method for property   success .
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property   errorCode.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property   errorCode .
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property   message.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property   message .
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property   data.
     *
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property   data .
     *
     * @param data value to be assigned to property data
     */
    public void setData(T data) {
        this.data = data;
    }
}