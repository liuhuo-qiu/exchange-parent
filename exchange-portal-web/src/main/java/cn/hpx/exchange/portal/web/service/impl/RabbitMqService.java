/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.portal.web.service.impl;

import cn.hpx.exchange.contant.RabbitChannelConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**  
 * @author qlj  
 * @version $Id: RabbitMqService.java, v 0.1 2018-05-24 12:03 legend Exp $$  
 */
public interface RabbitMqService {

    /**
     * 绑定 channel  把消息发送到 RabbitChannelConstant.HPX_ZIPKIN
     * @return
     */
    @Output(RabbitChannelConstant.HPX_ZIPKIN)
    MessageChannel getOutPut();

}