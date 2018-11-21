package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:25
 * @Version: 1.0.0.0
 */
public interface IlluminantobserverxyzService {
    /**
     * 无分页列表
     * @param
     * @return
     */
    public List<Illuminantobserverxyz> findAll();
    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(IlluminantobserverxyzModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(IlluminantobserverxyzModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getByWaveLengthAndAngle(IlluminantobserverxyzModel model);

}
