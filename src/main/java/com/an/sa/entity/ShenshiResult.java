package com.an.sa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShenshiResult {
    /**
     * same as SysVideo's id
     */
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date analyze_time;

    private byte[] result;
}
