package com.hc.sys.core.dye.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.DyeManufacturer;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeManufacturerModel;

import java.util.List;

/**
 * @Description: 染料厂商
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:12
 * @Version: 1.0.0.0
 */
public interface DyeManufacturerService {
    /**
     * 列表
     * @param model
     * @return
     */
    public Result list(DyeManufacturerModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<DyeManufacturer> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeManufacturerModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeManufacturerModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(DyeManufacturerModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeManufacturerModel model);
}
