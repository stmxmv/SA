package com.an.sa.mapper;

import com.an.sa.entity.SysVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 视频文件Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@Mapper
public interface SysVideoMapper extends BaseMapper<SysVideo>
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
     * 删除视频文件
     *
     * @param id 视频文件主键
     * @return 结果
     */
    public int deleteSysVideoById(Long id);

    /**
     * 批量删除视频文件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysVideoByIds(Long[] ids);
}
