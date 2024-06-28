package com.example.filewatcher.utils;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.api.gax.paging.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;

import java.time.*;

@Slf4j
public class GCPStorageUtils {
    public static void getData(String fileName) {
        Storage storage = StorageOptions.newBuilder().setProjectId("PROVIDE_PROJECT_ID").build().getService();
        Page<Blob> blobs = storage.list("PROVIDE_BUCKET_NAME");
        for (Blob blob : blobs.iterateAll()) {
            if (StringUtils.equals(fileName,blob.getName())){
                log.info("Created timestamp: {} ",toLocalDateDime(blob.getCreateTimeOffsetDateTime()));
                log.info("Updated timestamp: {} ",toLocalDateDime(blob.getUpdateTimeOffsetDateTime()));
            }
        }
    }

    private static String toLocalDateDime(OffsetDateTime offsetDateTime) {
        ZonedDateTime zoned = offsetDateTime.atZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        LocalDateTime athensWallTime = zoned.toLocalDateTime();
        return athensWallTime.toString();
    }

    public static String toLocalDateDime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("Asia/Kolkata")).toString();
    }
}
