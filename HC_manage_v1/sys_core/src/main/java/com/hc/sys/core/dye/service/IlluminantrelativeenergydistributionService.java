package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Illuminantrelativeenergydistribution;
import com.hc.sys.core.dye.model.IlluminantrelativeenergydistributionModel;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:28
 * @Version: 1.0.0.0
 */
public interface IlluminantrelativeenergydistributionService {

    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<Illuminantrelativeenergydistribution> findAll();
    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(IlluminantrelativeenergydistributionModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(IlluminantrelativeenergydistributionModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getByIdAndWaveLength(IlluminantrelativeenergydistributionModel model);
}
