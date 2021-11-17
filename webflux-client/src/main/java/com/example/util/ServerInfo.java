package com.example.util;

public class ServerInfo {

    private String url;

    public ServerInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "url='" + url + '\'' +
                '}';
    }
}
