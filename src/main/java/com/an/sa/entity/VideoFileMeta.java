package com.an.sa.entity;

import lombok.Data;

@Data
public class VideoFileMeta {
    public String version = "1";
    public String uuid;
    public String localFileName;
    public String originalFileName;
    public String md5;
}
