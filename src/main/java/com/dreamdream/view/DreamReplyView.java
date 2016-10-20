package com.dreamdream.view;

import java.util.Date;

import com.dreamdream.dao.base.BasePo;

public class DreamReplyView extends BasePo {
    private Integer replyId;
    private Integer messageId;
    private Integer dreamerId;
    private String content;
    private Integer replyDreamerId;
    private Date replyCreateTime;
    private Boolean replyStats;
    private String nickName;
    private String avatarUrl;
    private Boolean dreamerStats;
    private String replyNickName;
    private String replyAvatarUrl;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyDreamerId() {
        return replyDreamerId;
    }

    public void setReplyDreamerId(Integer replyDreamerId) {
        this.replyDreamerId = replyDreamerId;
    }

    public Date getReplyCreateTime() {
        return replyCreateTime;
    }

    public void setReplyCreateTime(Date replyCreateTime) {
        this.replyCreateTime = replyCreateTime;
    }

    public Boolean getReplyStats() {
        return replyStats;
    }

    public void setReplyStats(Boolean replyStats) {
        this.replyStats = replyStats;
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

    public String getReplyNickName() {
        return replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName;
    }

    public String getReplyAvatarUrl() {
        return replyAvatarUrl;
    }

    public void setReplyAvatarUrl(String replyAvatarUrl) {
        this.replyAvatarUrl = replyAvatarUrl;
    }

    @Override
    public String toString() {
        return "DreamReplyView [replyId=" + replyId + ", messageId=" + messageId + ", dreamerId="
                + dreamerId + ", content=" + content + ", replyDreamerId=" + replyDreamerId
                + ", replyCreateTime=" + replyCreateTime + ", replyStats=" + replyStats
                + ", nickName=" + nickName + ", avatarUrl=" + avatarUrl + ", dreamerStats="
                + dreamerStats + ", replyNickName=" + replyNickName + ", replyAvatarUrl="
                + replyAvatarUrl + "]";
    }

}
