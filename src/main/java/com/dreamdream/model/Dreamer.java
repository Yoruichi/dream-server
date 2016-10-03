package com.dreamdream.model;

import java.io.Serializable;
import java.util.Date;

import com.dreamdream.dao.BasePo;

@SuppressWarnings("serial")
public class Dreamer extends BasePo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stats;

    @Override
    public String toString() {
        return "Dreamer [id=" + id + ", username=" + username + ", password=" + password
                + ", phone=" + phone + ", email=" + email + ", nickName=" + nickName
                + ", avatarUrl=" + avatarUrl + ", gender=" + gender + ", createTime=" + createTime
                + ", lastUpdateTime=" + lastUpdateTime + ", stats=" + stats + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
