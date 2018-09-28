package com.wistronits.aml.commons.websocket;

import java.io.Serializable;
import java.util.Date;

/**
 * SessionUser
 * @author haibokong
 */
public class SessionUser implements Serializable {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Date loginDate;

    /**
     * 登录ip
     */
    private String loginIp;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * SessionUser
     */
    public SessionUser() {
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        SessionUser that = (SessionUser) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) {

            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {

            return false;
        }

        if (loginDate != null ? !loginDate.equals(that.loginDate) : that.loginDate != null) {

            return false;
        }
        return loginIp != null ? loginIp.equals(that.loginIp) : that.loginIp == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        return result;
    }

}
