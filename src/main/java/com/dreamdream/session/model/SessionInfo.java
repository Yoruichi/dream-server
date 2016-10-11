package com.dreamdream.session.model;

import java.io.Serializable;

import com.dreamdream.model.Dreamer;

@SuppressWarnings("serial")
public class SessionInfo implements Serializable {
    private Dreamer dreamer;
    private String ip;

    public Dreamer getDreamer() {
        return dreamer;
    }

    public void setDreamer(Dreamer dreamer) {
        this.dreamer = dreamer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SessionInfo [dreamer=" + dreamer + ", ip=" + ip + "]";
    }

}
