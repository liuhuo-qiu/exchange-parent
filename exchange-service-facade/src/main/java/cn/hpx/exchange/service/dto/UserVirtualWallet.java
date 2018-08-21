/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**  
 * @author qlj  
 * @version $Id: UserVirtualWallet.java, v 0.1 2018-05-07 10:17 legend Exp $$ 
 */
public class UserVirtualWallet implements Serializable {

    // 主键ID
    private Integer fid;
    // 用户ID
    private Integer fuid;
    // 虚拟币ID
    private Integer fcoinid;
    // 总资产
    private BigDecimal ftotal;
    // 冻结资产
    private BigDecimal ffrozen;
    // 借贷
    private BigDecimal fborrow;

    private BigDecimal flock;
    // 总资产
    private String ftotals;
    // 冻结资产
    private String ffrozens;
    // 借贷
    private String fborrows;
    // 更换时间
    private Date fupdatetime;

    /************ 扩展字段 ***************/

    // 登录名
    private String floginname;
    // 昵称
    private String fnickname;
    // 真实姓名
    private String frealname;
    // 虚拟币名称
    private String fcoinName;
    // 虚拟币logo
    private String coinlogo;

    // 虚拟币简称
    private String fshortname;

    // 转换成CNYT汇率
    private BigDecimal rateCnyt;

    // 转换成美元汇率
    private BigDecimal usdtCnyt;

    // 是否结算币种
    private boolean fissettle;

    private boolean hiddenZero;

    private Integer filterUserFlag;


    private BigDecimal ftotalInc = new BigDecimal("0.00");
    private BigDecimal ffrozenInc = new BigDecimal("0.00");

    private int version;


    /**
     * Getter method for property   fid.
     *
     * @return property value of fid
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * Setter method for property   fid .
     *
     * @param fid value to be assigned to property fid
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * Getter method for property   fuid.
     *
     * @return property value of fuid
     */
    public Integer getFuid() {
        return fuid;
    }

    /**
     * Setter method for property   fuid .
     *
     * @param fuid value to be assigned to property fuid
     */
    public void setFuid(Integer fuid) {
        this.fuid = fuid;
    }

    /**
     * Getter method for property   fcoinid.
     *
     * @return property value of fcoinid
     */
    public Integer getFcoinid() {
        return fcoinid;
    }

    /**
     * Setter method for property   fcoinid .
     *
     * @param fcoinid value to be assigned to property fcoinid
     */
    public void setFcoinid(Integer fcoinid) {
        this.fcoinid = fcoinid;
    }

    /**
     * Getter method for property   ftotal.
     *
     * @return property value of ftotal
     */
    public BigDecimal getFtotal() {
        return ftotal;
    }

    /**
     * Setter method for property   ftotal .
     *
     * @param ftotal value to be assigned to property ftotal
     */
    public void setFtotal(BigDecimal ftotal) {
        this.ftotal = ftotal;
    }

    /**
     * Getter method for property   ffrozen.
     *
     * @return property value of ffrozen
     */
    public BigDecimal getFfrozen() {
        return ffrozen;
    }

    /**
     * Setter method for property   ffrozen .
     *
     * @param ffrozen value to be assigned to property ffrozen
     */
    public void setFfrozen(BigDecimal ffrozen) {
        this.ffrozen = ffrozen;
    }

    /**
     * Getter method for property   fborrow.
     *
     * @return property value of fborrow
     */
    public BigDecimal getFborrow() {
        return fborrow;
    }

    /**
     * Setter method for property   fborrow .
     *
     * @param fborrow value to be assigned to property fborrow
     */
    public void setFborrow(BigDecimal fborrow) {
        this.fborrow = fborrow;
    }

    /**
     * Getter method for property   flock.
     *
     * @return property value of flock
     */
    public BigDecimal getFlock() {
        return flock;
    }

    /**
     * Setter method for property   flock .
     *
     * @param flock value to be assigned to property flock
     */
    public void setFlock(BigDecimal flock) {
        this.flock = flock;
    }

    /**
     * Getter method for property   ftotals.
     *
     * @return property value of ftotals
     */
    public String getFtotals() {
        return ftotals;
    }

    /**
     * Setter method for property   ftotals .
     *
     * @param ftotals value to be assigned to property ftotals
     */
    public void setFtotals(String ftotals) {
        this.ftotals = ftotals;
    }

    /**
     * Getter method for property   ffrozens.
     *
     * @return property value of ffrozens
     */
    public String getFfrozens() {
        return ffrozens;
    }

    /**
     * Setter method for property   ffrozens .
     *
     * @param ffrozens value to be assigned to property ffrozens
     */
    public void setFfrozens(String ffrozens) {
        this.ffrozens = ffrozens;
    }

    /**
     * Getter method for property   fborrows.
     *
     * @return property value of fborrows
     */
    public String getFborrows() {
        return fborrows;
    }

    /**
     * Setter method for property   fborrows .
     *
     * @param fborrows value to be assigned to property fborrows
     */
    public void setFborrows(String fborrows) {
        this.fborrows = fborrows;
    }

    /**
     * Getter method for property   fupdatetime.
     *
     * @return property value of fupdatetime
     */
    public Date getFupdatetime() {
        return fupdatetime;
    }

    /**
     * Setter method for property   fupdatetime .
     *
     * @param fupdatetime value to be assigned to property fupdatetime
     */
    public void setFupdatetime(Date fupdatetime) {
        this.fupdatetime = fupdatetime;
    }

    /**
     * Getter method for property   floginname.
     *
     * @return property value of floginname
     */
    public String getFloginname() {
        return floginname;
    }

    /**
     * Setter method for property   floginname .
     *
     * @param floginname value to be assigned to property floginname
     */
    public void setFloginname(String floginname) {
        this.floginname = floginname;
    }

    /**
     * Getter method for property   fnickname.
     *
     * @return property value of fnickname
     */
    public String getFnickname() {
        return fnickname;
    }

    /**
     * Setter method for property   fnickname .
     *
     * @param fnickname value to be assigned to property fnickname
     */
    public void setFnickname(String fnickname) {
        this.fnickname = fnickname;
    }

    /**
     * Getter method for property   frealname.
     *
     * @return property value of frealname
     */
    public String getFrealname() {
        return frealname;
    }

    /**
     * Setter method for property   frealname .
     *
     * @param frealname value to be assigned to property frealname
     */
    public void setFrealname(String frealname) {
        this.frealname = frealname;
    }

    /**
     * Getter method for property   fcoinName.
     *
     * @return property value of fcoinName
     */
    public String getFcoinName() {
        return fcoinName;
    }

    /**
     * Setter method for property   fcoinName .
     *
     * @param fcoinName value to be assigned to property fcoinName
     */
    public void setFcoinName(String fcoinName) {
        this.fcoinName = fcoinName;
    }

    /**
     * Getter method for property   coinlogo.
     *
     * @return property value of coinlogo
     */
    public String getCoinlogo() {
        return coinlogo;
    }

    /**
     * Setter method for property   coinlogo .
     *
     * @param coinlogo value to be assigned to property coinlogo
     */
    public void setCoinlogo(String coinlogo) {
        this.coinlogo = coinlogo;
    }

    /**
     * Getter method for property   fshortname.
     *
     * @return property value of fshortname
     */
    public String getFshortname() {
        return fshortname;
    }

    /**
     * Setter method for property   fshortname .
     *
     * @param fshortname value to be assigned to property fshortname
     */
    public void setFshortname(String fshortname) {
        this.fshortname = fshortname;
    }

    /**
     * Getter method for property   rateCnyt.
     *
     * @return property value of rateCnyt
     */
    public BigDecimal getRateCnyt() {
        return rateCnyt;
    }

    /**
     * Setter method for property   rateCnyt .
     *
     * @param rateCnyt value to be assigned to property rateCnyt
     */
    public void setRateCnyt(BigDecimal rateCnyt) {
        this.rateCnyt = rateCnyt;
    }

    /**
     * Getter method for property   usdtCnyt.
     *
     * @return property value of usdtCnyt
     */
    public BigDecimal getUsdtCnyt() {
        return usdtCnyt;
    }

    /**
     * Setter method for property   usdtCnyt .
     *
     * @param usdtCnyt value to be assigned to property usdtCnyt
     */
    public void setUsdtCnyt(BigDecimal usdtCnyt) {
        this.usdtCnyt = usdtCnyt;
    }

    /**
     * Getter method for property   fissettle.
     *
     * @return property value of fissettle
     */
    public boolean fissettle() {
        return fissettle;
    }

    /**
     * Setter method for property   fissettle .
     *
     * @param fissettle value to be assigned to property fissettle
     */
    public void setFissettle(boolean fissettle) {
        this.fissettle = fissettle;
    }

    /**
     * Getter method for property   hiddenZero.
     *
     * @return property value of hiddenZero
     */
    public boolean hiddenZero() {
        return hiddenZero;
    }

    /**
     * Setter method for property   hiddenZero .
     *
     * @param hiddenZero value to be assigned to property hiddenZero
     */
    public void setHiddenZero(boolean hiddenZero) {
        this.hiddenZero = hiddenZero;
    }

    /**
     * Getter method for property   filterUserFlag.
     *
     * @return property value of filterUserFlag
     */
    public Integer getFilterUserFlag() {
        return filterUserFlag;
    }

    /**
     * Setter method for property   filterUserFlag .
     *
     * @param filterUserFlag value to be assigned to property filterUserFlag
     */
    public void setFilterUserFlag(Integer filterUserFlag) {
        this.filterUserFlag = filterUserFlag;
    }

    /**
     * Getter method for property   ftotalInc.
     *
     * @return property value of ftotalInc
     */
    public BigDecimal getFtotalInc() {
        return ftotalInc;
    }

    /**
     * Setter method for property   ftotalInc .
     *
     * @param ftotalInc value to be assigned to property ftotalInc
     */
    public void setFtotalInc(BigDecimal ftotalInc) {
        this.ftotalInc = ftotalInc;
    }

    /**
     * Getter method for property   ffrozenInc.
     *
     * @return property value of ffrozenInc
     */
    public BigDecimal getFfrozenInc() {
        return ffrozenInc;
    }

    /**
     * Setter method for property   ffrozenInc .
     *
     * @param ffrozenInc value to be assigned to property ffrozenInc
     */
    public void setFfrozenInc(BigDecimal ffrozenInc) {
        this.ffrozenInc = ffrozenInc;
    }

    /**
     * Getter method for property   version.
     *
     * @return property value of version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter method for property   version .
     *
     * @param version value to be assigned to property version
     */
    public void setVersion(int version) {
        this.version = version;
    }
}