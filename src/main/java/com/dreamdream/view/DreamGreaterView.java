package com.dreamdream.view;

import java.util.Date;

import com.dreamdream.dao.base.BasePo;

public class DreamGreaterView extends BasePo {
    private Integer greaterId;
    private Integer messageId;
    private Integer dreamerId;
    private Date greaterCreateTime;
    private Boolean greaterStats;
    private String nickName;
    private String avatarUrl;
    private Boolean dreamerStats;

    public Integer getGreaterId() {
        return greaterId;
    }

    public void setGreaterId(Integer greaterId) {
        this.greaterId = greaterId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getDreamerId() {
        return dreamerId;
    }

    public void setDreamerId(Integer dreamerId) {
        this.dreamerId = dreamerId;
    }

    public Date getGreaterCreateTime() {
        return greaterCreateTime;
    }

    public void setGreaterCreateTime(Date greaterCreateTime) {
        this.greaterCreateTime = greaterCreateTime;
    }

    public Boolean getGreaterStats() {
        return greaterStats;
    }

    public void setGreaterStats(Boolean greaterStats) {
        this.greaterStats = greaterStats;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getDreamerStats() {
        return dreamerStats;
    }

    public void setDreamerStats(Boolean dreamerStats) {
        this.dreamerStats = dreamerStats;
    }

    @Override
    public String toString() {
        return "DreamGreaterView [greaterId=" + greaterId + ", messageId=" + messageId
                + ", dreamerId=" + dreamerId + ", greaterCreateTime=" + greaterCreateTime
                + ", greaterStats=" + greaterStats + ", nickName=" + nickName + ", avatarUrl="
                + avatarUrl + ", dreamerStats=" + dreamerStats + "]";
    }

}
