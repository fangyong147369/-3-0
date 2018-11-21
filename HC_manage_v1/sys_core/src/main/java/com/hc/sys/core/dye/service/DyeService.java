package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;
import java.util.List;

/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:13
 * @Version: 1.0.0.0
 */
public interface  DyeService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(DyeModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public  List<DyeModel> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeModel model);
    /**
     * 批量添加
     * @param list
     * @return
     */
    public Result addList(List<Dye> list);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeModel model);

    /**
     * 获取
     * @param model
     * @return
     */
    public Result getById(DyeModel model);

    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeModel model);

}
