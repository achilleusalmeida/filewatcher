package com.example.filewatcher.listener;

import com.example.filewatcher.utils.GCPStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;

import java.util.Set;

@Slf4j
public class MyFileChangeListener implements FileChangeListener {
    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles:changeSet) {
            for(ChangedFile cfile:cfiles.getFiles()) {
                log.info("File Name {} Operation {}",cfile.getFile().getName(),cfile.getType());
                //GCPStorageUtils.getData(cfile.getFile().getName());
            }
        }
    }
}
