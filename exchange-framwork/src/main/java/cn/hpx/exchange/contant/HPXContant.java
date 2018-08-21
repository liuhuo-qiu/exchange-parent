/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.contant;

import javafx.beans.DefaultProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**  
 * @author qlj  
 * @version $Id: HPXContant.java, v 0.1 2018-05-17 18:10 legend Exp $$  
 */
@ConfigurationProperties(prefix = HPXContant.prefix)
public class HPXContant {

    public static final String prefix="hpx.app";

    /**
     *
     */
    private String token;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private boolean web=false;


    /**
     * Getter method for property   web.
     *
     * @return property value of web
     */
    public boolean getWeb() {
        return web;
    }

    /**
     * Setter method for property   web .
     *
     * @param web value to be assigned to property web
     */
    public void setWeb(boolean web) {
        this.web = web;
    }

    /**
     * Getter method for property   token.
     *
     * @return property value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter method for property   token .
     *
     * @param token value to be assigned to property token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter method for property   name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property   name .
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }
}