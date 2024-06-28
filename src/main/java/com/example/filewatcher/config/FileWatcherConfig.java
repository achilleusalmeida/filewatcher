package com.example.filewatcher.config;

import com.example.filewatcher.listener.MyFileChangeListener;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.Duration;

@Slf4j
@Configuration
public class FileWatcherConfig {
    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(3000L));
        fileSystemWatcher.addSourceDirectory(new File("/vol1"));
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