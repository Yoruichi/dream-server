package com.dreamdream.model;

import java.io.Serializable;
import java.util.Date;

import com.dreamdream.dao.base.BasePo;

@SuppressWarnings("serial")
public class DreamGreater extends BasePo implements Serializable {
    private Integer id;
    private Integer messageId;
    private Integer dreamerId;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stats;

    @Override
    public String toString() {
        return "DreamGreater [id=" + id + ", messageId=" + messageId + ", dreamerId=" + dreamerId
                + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + ", stats="
                + stats + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
