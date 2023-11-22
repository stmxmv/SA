package com.an.sa.entity;

import lombok.Data;

@Data
public class ShenshiResult {
    /**
     * same as SysVideo's id
     */
    private Long id;
    private byte[] result;
}
