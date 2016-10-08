package com.dreamdream.session.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SessionInfo implements Serializable {
    private String ip;
    private String dreamerId;
    private String dreamerName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDreamerId() {
        return dreamerId;
    }

    public void setDreamerId(String dreamerId) {
        this.dreamerId = dreamerId;
    }

    public String getDreamerName() {
        return dreamerName;
    }

    public void setDreamerName(String dreamerName) {
        this.dreamerName = dreamerName;
    }

    @Override
    public String toString() {
        return "SessionInfo [ip=" + ip + ", dreamerId=" + dreamerId + ", dreamerName=" + dreamerName
                + "]";
    }

}
