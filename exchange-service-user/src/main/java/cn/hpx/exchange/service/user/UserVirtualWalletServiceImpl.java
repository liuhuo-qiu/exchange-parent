/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.user;

import cn.hpx.exchange.service.dto.UserVirtualWallet;
import cn.hpx.exchange.service.mapper.UserVirtualWalletMapper;
import cn.hpx.exchange.service.user.UserVirtualWalletService;
import cn.hpx.exchange.service.enums.BusinessOrderTypeEnum;
import cn.hpx.exchange.service.result.BaseResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**  
 * @author qlj  
 * @version $Id: UserVirtualWalletServiceImpl.java, v 0.1 2018-05-04 13:51 legend Exp $$  
 */
@RestController
@RequestMapping("/userVirtualWallet")
public class UserVirtualWalletServiceImpl implements UserVirtualWalletService {


    /**
     * 日志对象
     */
    private static final Logger logger= Logger.getLogger(UserVirtualWalletServiceImpl.class);

    /**
     * 钱包dao操作
     */
    @Autowired(required =false)
    private UserVirtualWalletMapper userVirtualWalletMapper;


    /**
     * 变更用户钱包余额数量(钱包加钱减钱，但是冻结金额不变)
     *      1.查询用户钱包信息
     *      2.判断钱包资金是否充足
     *      3.更新钱包资金变动(可用金额
     *      4.记录流水
     * @param uid                       用户id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            币种id/交易对id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @return       是否扣钱成功
     */
    @Override
    @RequestMapping(value="/changeDefault",method= RequestMethod.POST)
    public BaseResult<Boolean> changeUserVirtualWallet(Integer uid, BigDecimal amout,BigDecimal frazonChange, Integer coinId, BusinessOrderTypeEnum businessOrderTypeEnum,String coinSerialNo) {
        return changeUserVirtualWallet(uid,  amout,frazonChange , coinId,  businessOrderTypeEnum,coinSerialNo,false);
    }

    /**
     * 变更用户钱包余额数量,并且变更钱包冻结数量
     *      (钱包加钱，减钱，冻结金额根据是否变化由changeFrazone控制)
     *      1.查询用户钱包信息
     *      2.判断钱包资金是否充足
     *      3.更新钱包资金变动(可用金额/冻结金额)
     *          用户可用金额增加100则冻结金额相应要减去100
     *      4.记录流水
     * @param uid                       用户id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            币种id/交易对id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @param changeFrazone     是否变更冻结金额
     * @return       是否扣钱成功
     */
    @Override
    @RequestMapping(value="/changeAutoFrazone",method= RequestMethod.POST)
    public BaseResult<Boolean> changeUserVirtualWallet(Integer uid, BigDecimal amout,BigDecimal frazonChange, Integer coinId, BusinessOrderTypeEnum businessOrderTypeEnum,String coinSerialNo, boolean changeFrazone) {
        return changeUserVirtualWallet(uid,  amout,frazonChange , coinId,  businessOrderTypeEnum,coinSerialNo,changeFrazone,true);
    }

    /**
     * 变更用户钱包余额数量,并且变更钱包冻结数量
     *      (自由控制增加或者减少余额，或者增加或者减少冻结金额
     *          或者在增加或减少余额的同时减少或增加冻结金额)
     *      1.查询用户钱包信息
     *      2.判断钱包资金是否充足
     *      3.更新钱包资金变动(可用金额/冻结金额)
     *          用户可用金额增加100则冻结金额相应要减去100
     *      4.记录流水
     * @param uid                       用户id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            币种id/交易对id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @param changeFrazone     是否变更冻结金额  不能跟changeTotal同时为false
     * @param changeTotal     是否变更余额  changeTotal
     * @return       是否扣钱成功
     */
    @Override
    @RequestMapping(value="/autoChange",method= RequestMethod.POST)
    public BaseResult<Boolean> changeUserVirtualWallet(final Integer uid, final BigDecimal amout,BigDecimal frazonChange,final  Integer coinId,
                                                       final  BusinessOrderTypeEnum businessOrderTypeEnum,final String coinSerialNo,
                                                       final  boolean changeFrazone,final boolean changeTotal) {
        BaseResult<Boolean> result=new BaseResult<>();
        UserVirtualWallet byId = userVirtualWalletMapper.findById(10762649);
        return result;
    }

    /**
     * 根据钱包id查询钱包信息
     * @param id  钱包id
     * @return
     */
    @Override
    @RequestMapping(value="/findVirtualWalletById",method= {RequestMethod.POST,RequestMethod.GET})
    public BaseResult<UserVirtualWallet> findUserVirtualWalletById(String id) {
        logger.warn("获取到查询请求");
        BaseResult<UserVirtualWallet> result=new BaseResult<>();
        UserVirtualWallet virtualWallet = userVirtualWalletMapper.findById(Integer.valueOf(id));
        logger.warn("查询结果为"+JSONObject.toJSON(virtualWallet));
        result.setSuccess(true);
        result.setData(virtualWallet);
        return result;
    }
}