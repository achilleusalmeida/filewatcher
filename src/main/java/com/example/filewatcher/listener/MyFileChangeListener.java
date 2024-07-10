package com.example.filewatcher.listener;

import com.example.filewatcher.utils.GCPStorageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;

import java.util.Set;
import java.util.UUID;

import static org.springframework.boot.devtools.filewatch.ChangedFile.Type.DELETE;

public class MyFileChangeListener implements FileChangeListener {
    private static final Logger log = LogManager.getLogger(MyFileChangeListener.class);
    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles:changeSet) {
            for(ChangedFile cfile:cfiles.getFiles()) {
                String transactionId = UUID.randomUUID().toString();
                log.info("File Name {} Operation {} transactionId {}",cfile.getFile().getName(),cfile.getType(),transactionId);
                if(DELETE!=cfile.getType()){
                    log.info("Last Modified : {} transactionId {}",GCPStorageUtils.toLocalDateDime(cfile.getFile().lastModified()),transactionId);
                    GCPStorageUtils.moveFile(cfile.getFile(),transactionId);
                }
            }
        }
    }
}