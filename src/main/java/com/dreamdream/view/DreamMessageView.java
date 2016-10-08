package com.dreamdream.view;

import java.util.Date;

import com.dreamdream.dao.base.BasePo;

public class DreamMessageView extends BasePo {
    private Integer messageId;
    private Integer dreamerId;
    private String content;
    private String type;
    private String image_url;
    private Date messageCreateTime;
    private Boolean messageStats;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private Date dreamerCreateTime;
    private Boolean dreamerStats;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public Boolean getMessageStats() {
        return messageStats;
    }

    public void setMessageStats(Boolean messageStats) {
        this.messageStats = messageStats;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDreamerCreateTime() {
        return dreamerCreateTime;
    }

    public void setDreamerCreateTime(Date dreamerCreateTime) {
        this.dreamerCreateTime = dreamerCreateTime;
    }

    public Boolean getDreamerStats() {
        return dreamerStats;
    }

    public void setDreamerStats(Boolean dreamerStats) {
        this.dreamerStats = dreamerStats;
    }

    @Override
    public String toString() {
        return "MessageView [messageId=" + messageId + ", dreamerId=" + dreamerId + ", content="
                + content + ", type=" + type + ", image_url=" + image_url + ", messageCreateTime="
                + messageCreateTime + ", messageStats=" + messageStats + ", nickName=" + nickName
                + ", avatarUrl=" + avatarUrl + ", gender=" + gender + ", dreamerCreateTime="
                + dreamerCreateTime + ", dreamerStats=" + dreamerStats + "]";
    }

}
