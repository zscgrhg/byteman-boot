package com.example.tools.byteman.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Properties;

public class BootstrapListener implements SpringApplicationRunListener {

    public BootstrapListener(SpringApplication application, String... args) {
    }

    public void starting() {

        Properties p = new Properties();
        try {
            p.load(getClass().getClassLoader().getResourceAsStream("byteman.properties"));
        } catch (Exception e) {

        }
        String path = p.getProperty("folders", "");
        String[] folders = path.split("[:;]");
        BytemanProperties config = new BytemanProperties();
        config.setScriptFolders(Arrays.asList(folders));
        long interval = Long.parseLong(p.getProperty("interval", "3000"));
        config.setPollingInterval(interval);
        int port = Integer.parseInt(p.getProperty("port", "0"));
        if (port > 0) {
            config.setPort(port);
        }
        new BytemanInstaller(config).installByteman();
    }

    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    public void finished(ConfigurableApplicationContext context, Throwable exception) {

    }
}
