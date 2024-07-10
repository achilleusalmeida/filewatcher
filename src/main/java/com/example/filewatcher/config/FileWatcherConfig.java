package com.example.filewatcher.config;

import com.example.filewatcher.listener.MyFileChangeListener;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    private static final Logger log = LogManager.getLogger(FileWatcherConfig.class);
    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(3000L));
        fileSystemWatcher.addSourceDirectory(new File("/vol1/invoices"));
        fileSystemWatcher.addListener(new MyFileChangeListener());
        fileSystemWatcher.start();
        log.info("Started FileSystemWatcher");
        return fileSystemWatcher;
    }
    @PreDestroy
    public void onDestroy() throws Exception {
        fileSystemWatcher().stop();
    }
}