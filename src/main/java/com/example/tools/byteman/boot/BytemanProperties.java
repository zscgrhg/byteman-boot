package com.example.tools.byteman.boot;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;


public class BytemanProperties {
    private List<String> scriptFolders;
    private long pollingInterval=3000L;
    private int port=findFreeSocketPort();


    public List<String> getScriptFolders() {
        return scriptFolders;
    }

    public void setScriptFolders(List<String> scriptFolders) {
        this.scriptFolders = scriptFolders;
    }

    public long getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(long pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int findFreeSocketPort() {
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.close();
            return socket.getLocalPort();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "BytemanProperties{" +
                "scriptFolders=" + scriptFolders +
                ", pollingInterval=" + pollingInterval +
                ", port=" + port +
                '}';
    }
}
