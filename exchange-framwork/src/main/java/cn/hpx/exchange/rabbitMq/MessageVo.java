/**
 * HPX.com Inc.
 * Copyright (c) 2018-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.rabbitMq;

import org.apache.log4j.Logger;

import java.io.Serializable;

/** 
 *
 * @author qlj  
 * @version $Id: MessageVo.java, v 0.1
 *    2018-08-21 12:04 qlj Exp $$ 
 */
public class MessageVo<T> implements Serializable {
    /**
     * 日志对象
     **/
    private static final Logger logger = Logger.getLogger(MessageVo.class);

    /**
     * topic
     */
    private String topic;

    /**
     * eventCode
     */
    private String eventCode;

    /**
     * 消息体
     */
    private T t;


    /**
     * Getter  method  for  property      topic.
     *
     * @return property  value  of  topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method for property   topic .
     *
     * @param topic value to be assigned to property topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Getter  method  for  property      eventCode.
     *
     * @return property  value  of  eventCode
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * Setter method for property   eventCode .
     *
     * @param eventCode value to be assigned to property eventCode
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * Getter  method  for  property      t.
     *
     * @return property  value  of  t
     */
    public T getT() {
        return t;
    }

    /**
     * Setter method for property   t .
     *
     * @param t value to be assigned to property t
     */
    public void setT(T t) {
        this.t = t;
    }
}