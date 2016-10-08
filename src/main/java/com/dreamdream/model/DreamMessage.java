package com.dreamdream.model;

import java.io.Serializable;
import java.util.Date;

import com.dreamdream.dao.base.BasePo;

@SuppressWarnings("serial")
public class DreamMessage extends BasePo implements Serializable {
    private Integer id;
    private Integer dreamerId;
    private String type;
    private String content;
    private String imageUrl;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stats;
    private String dreamType;
    private String dreamTypeContent;
    private String dreamLocationContent;
    private String dreamLocation;

    public enum DreamMessageType {
        PUBLIC, PRIVATE
    }

    @Override
    public String toString() {
        return "DreamMessage [id=" + id + ", dreamerId=" + dreamerId + ", type=" + type
                + ", content=" + content + ", imageUrl=" + imageUrl + ", createTime=" + createTime
                + ", lastUpdateTime=" + lastUpdateTime + ", stats=" + stats + ", dreamType="
                + dreamType + ", dreamTypeContent=" + dreamTypeContent + ", dreamLocationContent="
                + dreamLocationContent + ", dreamLocation=" + dreamLocation + "]";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getDreamType() {
        return dreamType;
    }

    public void setDreamType(String dreamType) {
        this.dreamType = dreamType;
    }

    public String getDreamTypeContent() {
        return dreamTypeContent;
    }

    public void setDreamTypeContent(String dreamTypeContent) {
        this.dreamTypeContent = dreamTypeContent;
    }

    public String getDreamLocationContent() {
        return dreamLocationContent;
    }

    public void setDreamLocationContent(String dreamLocationContent) {
        this.dreamLocationContent = dreamLocationContent;
    }

    public String getDreamLocation() {
        return dreamLocation;
    }

    public void setDreamLocation(String dreamLocation) {
        this.dreamLocation = dreamLocation;
    }

}
