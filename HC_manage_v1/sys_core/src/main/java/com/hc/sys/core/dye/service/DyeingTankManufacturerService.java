package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import java.util.List;
/**
 * @Description: 染缸厂商
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:19
 * @Version: 1.0.0.0
 */
public interface DyeingTankManufacturerService {
    /**
     * 列表
     * @param model
     * @return
     */
    public Result list(DyeingTankManufacturerModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<DyeingTankManufacturer> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeingTankManufacturerModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeingTankManufacturerModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(DyeingTankManufacturerModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeingTankManufacturerModel model);
}
