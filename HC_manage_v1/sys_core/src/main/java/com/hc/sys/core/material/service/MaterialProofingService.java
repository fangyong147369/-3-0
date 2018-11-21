package com.hc.sys.core.material.service;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.MaterialProofing;
import com.hc.sys.core.material.model.MaterialProofingModel;

import java.util.List;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/11/5 11:10
 * @Version: 1.0.0.0
 */
public interface MaterialProofingService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Object list(MaterialProofingModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<MaterialProofingModel> findList();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(MaterialProofingModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(MaterialProofingModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(MaterialProofingModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(MaterialProofingModel model);

}
