package com.an.sa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysVideo {
    private Long id;
    private String uuid;
    private Long person_id;
    private String fileName;
    private String md5;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receive_time;
    private Boolean isValid;
    private Boolean fileDeleted;
    private Boolean isDeleted;
}
