package com.hc.sys.core.material.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.entity.Concentration;
import com.hc.sys.core.material.model.ConcentrationModel;
import java.util.List;
/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:56
 * @Version: 1.0.0.0
 */
public interface ConcentrationService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(ConcentrationModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<Concentration> findList();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(ConcentrationModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(ConcentrationModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(ConcentrationModel model);
}
