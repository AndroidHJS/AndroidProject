package com.jack.login.Base;

import java.io.Serializable;

/**
 * Created by jack on 2017/1/5.
 */

public class User implements Serializable {
    private String openId;
    private String nickname;
    private int sex;
    private String headImageUrl;



    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", headImageUrl='" + headImageUrl + '\'' +
                '}';
    }
}
