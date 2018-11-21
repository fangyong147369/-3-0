package com.hc.sys.core.dye.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import com.hc.sys.core.dye.model.IlluminantwhitexyzModel;

import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:25
 * @Version: 1.0.0.0
 */
public interface IlluminantwhitexyzService {
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<Illuminantwhitexyz> findAll();
    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(IlluminantwhitexyzModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(IlluminantwhitexyzModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getByIdAndAngle(IlluminantwhitexyzModel model);

}
