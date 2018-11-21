package com.hc.sys.core.dye.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.DyeColor;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.dye.model.DyeColorModel;
import com.hc.sys.core.dye.model.DyeGroupModel;

import java.util.List;

/**
 * @Description: 染料颜色
 * @Author: fangyong
 * @CreateDate: 2018/11/5 11:13
 * @Version: 1.0.0.0
 */
public interface DyeColorService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(DyeColorModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public  List<DyeColor> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeColorModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeColorModel model);

    /**
     *  web专用
     * @param model
     * @return
     */
    public Result getById(DyeColorModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeColorModel model);

}
