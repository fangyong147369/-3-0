package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.MaterialTypeModel;
import java.util.List;
/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:58
 * @Version: 1.0.0.0
 */
public interface MaterialTypeService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(MaterialTypeModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<MaterialType> findList();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(MaterialTypeModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(MaterialTypeModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(MaterialTypeModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(MaterialTypeModel model);
}
