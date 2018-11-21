package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.DyeingTank;
import com.hc.sys.core.dye.model.DyeingTankModel;
import java.util.List;
/**
 * @Description: 染缸
 * @Author: fangyong
 * @CreateDate: 2018/11/1 12:31
 * @Version: 1.0.0.0
 */
public interface DyeingTankService {
    /**
     * 列表
     * @param model
     * @return
     */
    public Result list(DyeingTankModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<DyeingTank> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeingTankModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeingTankModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(DyeingTankModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeingTankModel model);
}
