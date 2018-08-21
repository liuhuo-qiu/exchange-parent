/**
 * HPX.com Inc.
 * Copyright (c) 2018-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.rabbitMq;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

/** 
 *
 * @author qlj  
 * @version $Id: RabbitMqProducter.java, v 0.1
 *    2018-08-21 12:01 qlj Exp $$ 
 */
public abstract class RabbitMqProducter {
    /**
     * 日志对象
     **/
    private static final Logger logger = Logger.getLogger(RabbitMqProducter.class);


    /**
     * 发送mq消息
     * @param messageVo
     */
    protected void  send(MessageVo messageVo){
        send(messageVo,getHeaders());
    }

    /**
     * 发送mq消息，自定义header
     * @param messageVo
     * @param header
     */
    protected void  send(MessageVo messageVo,Map<String,Object> header){
        send(getOutPut(),messageVo,header);
    }

    /**
     * 发送mq消息，自定义outPut，自定义header
     * @param messageVo
     * @param header
     */
    protected void  send(MessageChannel outPut,MessageVo messageVo,Map<String,Object> header){
        MessageHeaders messageHeaders = new MessageHeaders(header);
        Message<MessageVo> message = MessageBuilder.createMessage(messageVo, messageHeaders);

        MessageBuilder<MessageVo> builder = MessageBuilder.fromMessage(message);
        Message<MessageVo> build = builder.build();
        outPut.send(build);
    }

    /**
     * 指定header
     * @return
     */
    protected Map<String,Object> getHeaders(){
        return new HashMap<>();
    }

    /**
     * 获取输出流
     * @return
     */
    protected abstract MessageChannel getOutPut();

}