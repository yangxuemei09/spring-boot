package com.tangdi.common.model.po;

public class PubEmailConfig {
    private String emailConfigId;

    private String fromUser;

    private String host;

    private String pass;

    private String port;

    private String user;

    public String getEmailConfigId() {
        return emailConfigId;
    }

    public void setEmailConfigId(String emailConfigId) {
        this.emailConfigId = emailConfigId == null ? null : emailConfigId.trim();
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser == null ? null : fromUser.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }
}