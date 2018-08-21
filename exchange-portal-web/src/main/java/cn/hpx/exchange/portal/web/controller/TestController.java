/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.portal.web.controller;

import cn.hpx.exchange.portal.web.service.impl.RabbitMQPruducer;
import cn.hpx.exchange.portal.web.service.impl.UserVirtualWalletServiceI;
import cn.hpx.exchange.util.RedisHelper;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**  
 * @author qlj  
 * @version $Id: TestController.java, v 0.1 2018-05-03 17:16 legend Exp $$  
 */
@RestController
@RefreshScope
public class TestController {

    /**
     * 日志对象
     */
    private static final Logger logger= Logger.getLogger(TestController.class);

    @Value("${hpx.app.token}")
    private String form;


    @Autowired
    private RedisHelper redisHelper;


    @Autowired
    private StringRedisTemplate redisTemplate;



    /**
     *
     */
    @Autowired
    private UserVirtualWalletServiceI userVirtualWalletService;

    @Autowired
    private RabbitMQPruducer rabbitMQPruducer;

    @RequestMapping(value="/test",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object testController(HttpServletRequest request){
        logger.warn("开始查询-----------------");
        JSONObject object=new JSONObject();
        object.put("message","sucesss");
        object.put("testData",form);
        JSONObject jsonObject = new JSONObject();
        jsonObject .put("text","ppppppp111111111111111s");
        redisHelper.set("ARGS_AUTOMATIC_DING_URL",jsonObject.toJSONString());

        object.put("redisData",redisHelper.getValue("ARGS_AUTOMATIC_DING_URL"));
//        BaseResult<UserVirtualWallet> userVirtualWalletById = userVirtualWalletService.findUserVirtualWalletById("10762649");
//
//        logger.warn("获得的其结果为-----------------"+userVirtualWalletById);
//        object.put("data",userVirtualWalletById);
        object.put("mqResult",rabbitMQPruducer.sendToZipKin("1111111111111111111111"));
        return object;
    }
}