package com.an.sa.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.an.sa.Result;
import com.an.sa.dto.VideoAnalyzeProcess;
import com.an.sa.entity.SysVideo;
import com.an.sa.entity.VideoFileMeta;
import com.an.sa.exception.StorageFileNotFoundException;
import com.an.sa.service.ISysVideoService;
import com.an.sa.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    private final StorageService storageService;

    @Autowired
    private ISysVideoService sysVideoService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/list")
    public List<String> listUploadedFiles() {
        return storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());
    }

    @PostMapping("/getUploadedFileURL")
    public Result<String> getUploadedFileURL(@RequestParam("uuid") String uuid)
    {
        VideoFileMeta meta = storageService.getMetaByUUID(uuid);
        if (meta != null) {
            return Result.success(MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", meta.localFileName).build().toUri().toString());
        }
        return Result.error(1, "Fail to get meta of " + uuid);
    }

    @PostMapping("/getUploadedFileMeta")
    public Result<VideoFileMeta> getUploadedFileMeta(@RequestParam("uuid") String uuid)
    {
        VideoFileMeta meta = storageService.getMetaByUUID(uuid);
        if (meta != null) {
            return Result.success(meta);
        }
        return Result.error(1, "Fail to get meta of " + uuid);
    }


    @PostMapping("/getPersonVideoList")
    public Result<List<SysVideo>> getPersonVideoList(@RequestParam("person_id") Long person_id) {
        return Result.success(sysVideoService.getPersonVideoList(person_id));
    }

//    @PostMapping("/getVideoAnalyzeProcessByUUID")
//    public Result<VideoAnalyzeProcess> getVideoAnalyzeProcessByUUID(@RequestParam("uuid") String uuid) {
//
//    }


    /**
     *
     * @param file
     * @return file's uuid
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                           @RequestParam("person_id") Long person_id) throws IOException {

        InputStream inputStream = file.getInputStream();
        String md5Value = SecureUtil.md5(inputStream);
        inputStream.close();


        if (sysVideoService.selectSysVideoByMD5(md5Value) != null)
        {
            return Result.error(1, "File already exist");
        }

        String fileUUID = IdUtil.fastSimpleUUID();

        while (sysVideoService.selectSysVideoByUUID(fileUUID) != null) {
            fileUUID = IdUtil.fastSimpleUUID();
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        SysVideo video = new SysVideo();
        video.setUuid(fileUUID);
        video.setPerson_id(person_id);
        video.setFileName(file.getOriginalFilename());
        video.setMd5(md5Value);
        video.setIsValid(true);
        video.setIsDeleted(false);
        video.setFileDeleted(false);

        VideoFileMeta videoFileMeta = new VideoFileMeta();
        videoFileMeta.localFileName = fileUUID + "." + extension;
        videoFileMeta.originalFileName = file.getOriginalFilename();
        videoFileMeta.uuid = fileUUID;
        videoFileMeta.md5 = md5Value;

        storageService.store(file, videoFileMeta);

        sysVideoService.insertSysVideo(video);

        // send to analyze message queue


        return Result.success(fileUUID);
    }


    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
