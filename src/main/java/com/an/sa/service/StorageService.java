package com.an.sa.service;

import com.an.sa.entity.VideoFileMeta;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, VideoFileMeta meta);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    VideoFileMeta getMetaByUUID(String uuid);
}
