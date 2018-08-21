/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.portal.web.service.impl;

import cn.hpx.exchange.rabbitMq.RabbitMqProducter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**  
 * @author qlj  
 * @version $Id: WebRabbitMQPruducer.java, v 0.1 2018-05-24 10:45 legend Exp $$ 
 */
@EnableBinding(RabbitMqService.class)
@Component
public class WebRabbitMQPruducer extends RabbitMqProducter {


    @Autowired(required = false)
    RabbitMqService rabbitMqService;


    /**
     * 获取默认输出流
     * @return
     */
    @Override
    protected MessageChannel getOutPut() {
        return rabbitMqService.getOutPut();
    }

    /**
     * 构造默认的header
     * @return
     */
    @Override
    protected Map<String, Object> getHeaders() {
        Map<String,Object> header=new HashMap<>();
        header.put("exchange_module_name","web");
        return header;
    }
}