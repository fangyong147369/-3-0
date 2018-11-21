package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.model.FormulaModel;
import java.util.List;
/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:57
 * @Version: 1.0.0.0
 */
public interface FormulaService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(FormulaModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<FormulaModel> findList();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(FormulaModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(FormulaModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(FormulaModel model);
    /**
     * 通过配方名称查询
     * @param name
     * @return
     */
    public boolean getByName(String name);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(FormulaModel model);
}

