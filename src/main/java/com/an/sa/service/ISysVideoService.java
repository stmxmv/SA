package com.an.sa.service;

import java.util.List;
import com.an.sa.entity.SysVideo;

/**
 * 视频文件Service接口
 *
 * @author ruoyi
 * @date 2023-11-21
 */
public interface ISysVideoService
{
    /**
     * 查询视频文件
     *
     * @param id 视频文件主键
     * @return 视频文件
     */
    public SysVideo selectSysVideoById(Long id);


    public SysVideo selectSysVideoByUUID(String uuid);

    public SysVideo selectSysVideoByMD5(String md5);

    public List<SysVideo> getPersonVideoList(Long person_id);

    /**
     * 查询视频文件列表
     *
     * @param sysVideo 视频文件
     * @return 视频文件集合
     */
    public List<SysVideo> selectSysVideoList(SysVideo sysVideo);

    /**
     * 新增视频文件
     *
     * @param sysVideo 视频文件
     * @return 结果
     */
    public int insertSysVideo(SysVideo sysVideo);

    /**
     * 修改视频文件
     *
     * @param sysVideo 视频文件
     * @return 结果
     */
    public int updateSysVideo(SysVideo sysVideo);

    /**
     * 批量删除视频文件
     *
     * @param ids 需要删除的视频文件主键集合
     * @return 结果
     */
    public int deleteSysVideoByIds(Long[] ids);

    /**
     * 删除视频文件信息
     *
     * @param id 视频文件主键
     * @return 结果
     */
    public int deleteSysVideoById(Long id);
}
