package com.an.sa.dto;

import com.an.sa.entity.VideoFileMeta;
import lombok.Data;

@Data
public class VideoAnalyzeInfo {
    private Long id;
    private VideoFileMeta meta;
}
