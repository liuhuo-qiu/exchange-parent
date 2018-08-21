/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.user;

import cn.hpx.exchange.service.dto.UserVirtualWallet;
import cn.hpx.exchange.service.enums.BusinessOrderTypeEnum;
import cn.hpx.exchange.service.result.BaseResult;

import java.math.BigDecimal;

/**  
 * 用户钱包服务
 * @author qlj  
 * @version $Id: UserVirtualWalletService.java, v 0.1 2018-05-04 13:42 legend Exp $$  
 */
public interface UserVirtualWalletService {
    /**
     * 变更用户钱包余额数量
     *      (钱包加钱减钱，但是冻结金额不变)
     * @param uid                       用户id
     * @param coinId                    币种id/交易对id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            交易队id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @return       是否扣钱成功
     */
     BaseResult<Boolean> changeUserVirtualWallet(Integer uid, BigDecimal amout, BigDecimal frazonChange, Integer coinId,
                                                       BusinessOrderTypeEnum businessOrderTypeEnum, String coinSerialNo);

    /**
     * 变更用户钱包余额数量,并且变更钱包冻结数量
     *      (钱包加钱，减钱，冻结金额根据是否变化由changeFrazone控制,
     *          在增加或减少余额的同时减少或增加冻结金额)
     * @param uid                       用户id
     * @param coinId            币种id/交易对id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            货币类型id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @param changeFrazone     是否变更冻结金额
     * @return       是否扣钱成功
     */
     BaseResult<Boolean> changeUserVirtualWallet(Integer uid, BigDecimal amout,BigDecimal frazonChange, Integer coinId,
                                                       BusinessOrderTypeEnum businessOrderTypeEnum,String coinSerialNo,boolean changeFrazone);


    /**
     * 变更用户钱包余额数量,并且变更钱包冻结数量
     *      (自由控制增加或者减少余额，或者增加或者减少冻结金额
     *          或者在增加或减少余额的同时减少或增加冻结金额)
     * @param uid                       用户id
     * @param amout                     变更数量，如果是扣钱则传入参数需要调用amout.negate()
     * @param frazonChange          冻结变更数量
     * @param coinId            币种id/交易对id
     * @param businessOrderTypeEnum     本次扣钱操作的业务类型
     * @param coinSerialNo     本次扣钱操作的业务订单号
     * @param changeFrazone     是否变更冻结金额  不能跟changeTotal同时为false
     * @param changeTotal     是否变更余额  changeTotal
     * @return       是否扣钱成功s
     */
     BaseResult<Boolean> changeUserVirtualWallet(Integer uid, BigDecimal amout,BigDecimal frazonChange,  Integer coinId,
                                                         BusinessOrderTypeEnum businessOrderTypeEnum,String coinSerialNo,
                                                         boolean changeFrazone, boolean changeTotal);


    /**
     * 根据钱包id查询钱包信息
     * @param id  钱包id
     * @return
     */
     BaseResult<UserVirtualWallet> findUserVirtualWalletById(String id);
}