package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.model.FactoryModel;
import java.util.List;
/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 13:57
 * @Version: 1.0.0.0
 */
public interface FactoryService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Object list(FactoryModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<Factory> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(FactoryModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(FactoryModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(FactoryModel model);
}
