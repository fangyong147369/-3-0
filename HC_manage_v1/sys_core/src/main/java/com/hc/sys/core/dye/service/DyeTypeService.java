package com.hc.sys.core.dye.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.entity.DyeType;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.model.DyeTypeModel;

import java.util.List;

/**
 * @Description: 染料类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:13
 * @Version: 1.0.0.0
 */
public interface DyeTypeService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(DyeTypeModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public  List<DyeType> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeTypeModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeTypeModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(DyeTypeModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeTypeModel model);
}
