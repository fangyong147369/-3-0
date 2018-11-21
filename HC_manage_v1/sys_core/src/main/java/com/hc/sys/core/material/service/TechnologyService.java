package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.Technology;
import com.hc.sys.core.material.model.TechnologyModel;
import java.util.List;

/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:56
 * @Version: 1.0.0.0
 */
public interface TechnologyService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(TechnologyModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<TechnologyModel> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(TechnologyModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(TechnologyModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(TechnologyModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(TechnologyModel model);
}
