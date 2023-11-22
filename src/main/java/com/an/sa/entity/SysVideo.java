package com.an.sa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SysVideo {
    private Long id;
    private String uuid;
    private Long person_id;
    private String fileName;
    private String md5;
    private Boolean isValid;
    private Boolean fileDeleted;
    private Boolean isDeleted;
}
