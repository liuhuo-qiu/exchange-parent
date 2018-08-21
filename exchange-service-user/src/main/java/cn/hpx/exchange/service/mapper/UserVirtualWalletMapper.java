/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.mapper;

import cn.hpx.exchange.service.dto.UserVirtualWallet;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**  
 * @author qlj  
 * @version $Id: UserVirtualWalletMapper.java, v 0.1 2018-05-07 10:11 legend Exp $$  
 */
public interface UserVirtualWalletMapper{

    @Select("SELECT * FROM f_user_virtual_wallet WHERE fid = #{id}")
    UserVirtualWallet findById(Integer id);
}