package com.an.sa.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.an.sa.mapper.SysVideoMapper;
import com.an.sa.entity.SysVideo;
import com.an.sa.service.ISysVideoService;

/**
 * 视频文件Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@Service
public class SysVideoServiceImpl implements ISysVideoService
{
    @Autowired
    private SysVideoMapper sysVideoMapper;

    /**
     * 查询视频文件
     *
     * @param id 视频文件主键
     * @return 视频文件
     */
    @Override
    public SysVideo selectSysVideoById(Long id)
    {
        return sysVideoMapper.selectSysVideoById(id);
    }

    @Override
    public SysVideo selectSysVideoByUUID(String uuid)
    {
        return sysVideoMapper.selectSysVideoByUUID(uuid);
    }

    @Override
    public SysVideo selectSysVideoByMD5(String md5)
    {
        return sysVideoMapper.selectSysVideoByMD5(md5);
    }


    @Override
    public List<SysVideo> getPersonVideoList(Long person_id) {
        QueryWrapper<SysVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("person_id", person_id);
        return sysVideoMapper.selectList(wrapper);
    }

    /**
     * 查询视频文件列表
     *
     * @param sysVideo 视频文件
     * @return 视频文件
     */
    @Override
    public List<SysVideo> selectSysVideoList(SysVideo sysVideo)
    {
        return sysVideoMapper.selectSysVideoList(sysVideo);
    }

    /**
     * 新增视频文件
     *
     * @param sysVideo 视频文件
     * @return 结果
     */
    @Override
    public int insertSysVideo(SysVideo sysVideo)
    {
        return sysVideoMapper.insertSysVideo(sysVideo);
    }

    /**
     * 修改视频文件
     *
     * @param sysVideo 视频文件
     * @return 结果
     */
    @Override
    public int updateSysVideo(SysVideo sysVideo)
    {
        return sysVideoMapper.updateSysVideo(sysVideo);
    }

    /**
     * 批量删除视频文件
     *
     * @param ids 需要删除的视频文件主键
     * @return 结果
     */
    @Override
    public int deleteSysVideoByIds(Long[] ids)
    {
        return sysVideoMapper.deleteSysVideoByIds(ids);
    }

    /**
     * 删除视频文件信息
     *
     * @param id 视频文件主键
     * @return 结果
     */
    @Override
    public int deleteSysVideoById(Long id)
    {
        return sysVideoMapper.deleteSysVideoById(id);
    }
}
