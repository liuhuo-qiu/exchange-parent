/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.enums;

/**  
 * @author qlj  
 * @version $Id: BusinessOrderTypeEnum.java, v 0.1 2018-05-04 13:44 legend Exp $$  
 */
public enum BusinessOrderTypeEnum {

        SELL("1", "卖单撮合成功"),
        BUY("2", "买单撮合成功"),
        FEE("3", "收取手续费"),
        BUY_REFUND("4", "买方退还金额"),
        CNY_RECHARGE("5", "人民币充值"),
        CNY_WITHDRAWALS("6", "人民币提现"),
        CANCEL_WITHDRAWALS("7", "取消人民币提现"),
        USER_WITHDRAWALS("8", "用户提现人民币"),
        USER_COIN_WITHDRAWALS("9", "用户提现虚拟币"),
        USER_CANCEL_WITHDRAWALS("10", "用户撤销提现人民币"),
        USER_CANCEL_COIN_WITHDRAWALS("11", "用户撤销提现虚拟币"),
        INERTRANSFER_TARGET("12", "平台互转对方钱包更新"),
        COIN_WITHDRAWALS("13", "提币审核"),
        CANCEL_BUY_ENTRUST("14", "用户撤销买单"),
        CANCEL_SELL_ENTRUST("15", "用户撤销卖单"),
        ENTRUST_BUY("16", "委托买单"),
        ENTRUST_SELL("17", "委托卖单"),
        CANCEL_COIN_WITHDRAWALS("18", "后台撤销提现虚拟币"),
        EXAMINE_COIN_RECHARGE("19", "审核手工充值"),
        GRANT_COIN_RECHARGE("20", "发放手工充值"),
        GRANT_RECHARGE("21", "审核发放手工充值人民币"),
        WIN_GRANT("22", "WIN派发"),
        MANUAL_ADJUSTMENT("23", "手动调账"),
        JOB_UPDATE("24", "job更新"),
        CANCEL_ENTRUST("25", "撤销委单"),
        AUDIT_CONSOLE("26", "审核操作更新钱包，但数值未改变"),
        BATCH_ENTRUST_BUY("27", "批量委托买单"),
        BATCH_ENTRUST_SELL("28", "批量委托卖单"),
        REBATE_CHARGE("29", "返佣佣金"),
        C2C_BUY("30", "C2C,买单"),
        C2C_SELL("31", "C2C,下卖单"),
        C2C_BUY_SURE("32", "C2C,确认买单"),
        C2C_SELL_SURE("33", "C2C,确认卖单"),
        C2C_SELL_CANCEL("34", "C2C,取消卖单"),
        COIN_AIRDROP("34", "管理员空投"),
        ;
        BusinessOrderTypeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        private String code;
        private String value;


        /**
         * Getter method for property   code.
         *
         * @return property value of code
         */
        public String getCode() {
            return code;
        }

        /**
         * Setter method for property   code .
         *
         * @param code value to be assigned to property code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * Getter method for property   value.
         *
         * @return property value of value
         */
        public String getValue() {
            return value;
        }

        /**
         * Setter method for property   value .
         *
         * @param value value to be assigned to property value
         */
        public void setValue(String value) {
            this.value = value;
        }
    }