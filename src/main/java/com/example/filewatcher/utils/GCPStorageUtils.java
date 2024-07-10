package com.example.filewatcher.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GCPStorageUtils {
    private static final Logger log = LogManager.getLogger(GCPStorageUtils.class);

    public static String toLocalDateDime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Kolkata")).toString();
    }

    public static void moveFile(File originalFile,String transactionId) {
        Path source = Paths.get(originalFile.getAbsolutePath());
        Path newDir = Paths.get("/vol2/success/");
        log.info("Moving file [{}] to [{}] transactionId {}",originalFile.getAbsolutePath(),newDir,transactionId);
        try {
            Files.move(source, newDir.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            log.info("File uploaded successfully to [{}] transactionId {}", newDir,transactionId);
        }catch (NoSuchFileException e) {
            log.info("Destination directory [{}] does not exist transactionId {}",newDir,transactionId);
        }catch (IOException e) {
            log.info("Failed to upload file transactionId {}",transactionId);
        }
    }
}