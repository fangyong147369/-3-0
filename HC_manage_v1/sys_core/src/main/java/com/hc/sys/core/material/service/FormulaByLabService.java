package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.FormulaByLab;
import com.hc.sys.core.material.model.FormulaByLabModel;
import java.util.List;
/**
 * @Description: 配方查询
 * @Author: fangyong
 * @CreateDate: 2018/11/17 16:46
 * @Version: 1.0.0.0
 */
public interface FormulaByLabService {
    /**
     * @description 配方列表
     * @author: fangyong
     * @date 2018/11/17 16:50
     */
    List<FormulaByLab> all();
    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(FormulaByLabModel model);
    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(FormulaByLabModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(FormulaByLabModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(FormulaByLabModel model);
}
