/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.rabbit;

import cn.hpx.exchange.contant.RabbitChannelConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**  
 * @author qlj  
 * @version $Id: RabbitLogConsumer.java, v 0.1 2018-05-24 10:53 legend Exp $$  
 */
@EnableBinding
public class RabbitLogConsumer {

    private static final Logger logger= LoggerFactory.getLogger(RabbitLogConsumer.class);


    @StreamListener(RabbitChannelConstant.HPX_ZIPKIN)
    public void receiveLog(Message<String> message){
        String payload = message.getPayload();
        logger.warn("接收到mq消息:{}",payload);
    }

}