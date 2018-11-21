package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.dye.model.DyeGroupModel;
import java.util.List;
/**
 * @Description: 染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:13
 * @Version: 1.0.0.0
 */
public interface DyeGroupService {
    /**
     * 分页列表
     * @param model
     * @return
     */
    public Result list(DyeGroupModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    public  List<DyeGroup> findAll();

    /**
     * 添加
     * @param model
     * @return
     */
    public Result add(DyeGroupModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    public Result update(DyeGroupModel model);

    /**
     *  web专用
     * @param model
     * @return
     */
    public Result getById(DyeGroupModel model);
    /**
     * 删除
     * @param model
     * @return
     */
    public Result deleteById(DyeGroupModel model);
}
