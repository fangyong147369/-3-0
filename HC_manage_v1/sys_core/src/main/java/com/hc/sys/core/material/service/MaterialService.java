package com.hc.sys.core.material.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.Material;
import com.hc.sys.core.material.model.MaterialModel;
import java.util.List;

/**
 * @Description: 材质
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:41
 * @Version: 1.0.0.0
 */
public interface MaterialService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(MaterialModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<MaterialModel> findList();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(MaterialModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(MaterialModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(MaterialModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(MaterialModel model);
}
