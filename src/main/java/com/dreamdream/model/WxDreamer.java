package com.dreamdream.model;

import java.io.Serializable;

import com.dreamdream.dao.BasePo;

@SuppressWarnings("serial")
public class WxDreamer extends BasePo implements Serializable {
    private Integer dreamerId;
    private String openid;

    @Override
    public String toString() {
        return "WxDreamer [dreamerId=" + dreamerId + ", openid=" + openid + "]";
    }

    public Integer getDreamerId() {
        return dreamerId;
    }

    public void setDreamerId(Integer dreamerId) {
        this.dreamerId = dreamerId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
