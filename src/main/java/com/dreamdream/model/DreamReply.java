package com.dreamdream.model;

import java.io.Serializable;
import java.util.Date;

import com.dreamdream.dao.base.BasePo;

@SuppressWarnings("serial")
public class DreamReply extends BasePo implements Serializable {
    private Integer id;
    private Integer dreamerId;
    private Integer messageId;
    private String content;
    private Integer replyDreamerId;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stats;

    @Override
    public String toString() {
        return "MessageReply [id=" + id + ", dreamerId=" + dreamerId + ", messageId=" + messageId
                + ", content=" + content + ", replyDreamerId=" + replyDreamerId + ", createTime="
                + createTime + ", lastUpdateTime=" + lastUpdateTime + ", stats=" + stats + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDreamerId() {
        return dreamerId;
    }

    public void setDreamerId(Integer dreamerId) {
        this.dreamerId = dreamerId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getStats() {
        return stats;
    }

    public void setStats(Boolean stats) {
        this.stats = stats;
    }

}
