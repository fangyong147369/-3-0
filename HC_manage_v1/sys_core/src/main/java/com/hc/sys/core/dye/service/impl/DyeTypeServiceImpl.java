package com.hc.sys.core.dye.service.impl;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeTypeDao;
import com.hc.sys.core.dye.entity.DyeType;
import com.hc.sys.core.dye.model.DyeTypeModel;
import com.hc.sys.core.dye.service.DyeTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染料类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:17
 * @Version: 1.0.0.0
 */
@Service
public class DyeTypeServiceImpl implements DyeTypeService {
    @Resource
    public DyeTypeDao dyeTypeDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    public Result list(DyeTypeModel model) {
        PageDataList<DyeType> pageDataList = dyeTypeDao.list(model);
        PageDataList<DyeTypeModel> pageDataList_ = new PageDataList<DyeTypeModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeTypeModel> list = new ArrayList<DyeTypeModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (DyeType dyeType : pageDataList.getList()) {
                DyeTypeModel model_ = DyeTypeModel.instance(dyeType);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 无分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public List<DyeType> findAll() {
        return dyeTypeDao.findAll();
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(DyeTypeModel model) {
         checkParams(model);//参数检验
//        if (StringUtil.isBlank(model.getDescription())) {
//            return Result.error("描述不能为空！");
//        }
        DyeType dyeType=dyeTypeDao.save(model.prototype());
        return  Result.success().setData(dyeType);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(DyeTypeModel model) {
        checkParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        dyeTypeDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(DyeTypeModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        DyeType dyeType = dyeTypeDao.find(model.getId());
        if (null == dyeType) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeType);
    }

    @Override
    public Result deleteById(DyeTypeModel model) {
        if (model.getId() <= 0) {
            return  Result.error("ID参数错误");
        }
        DyeType dyeType = dyeTypeDao.find(model.getId());
        if (null == dyeType) {
            return   Result.error("ID参数错误！");
        }
        dyeTypeDao.delete(model.getId());
        return Result.success();
    }


    /**
     * 参数检验
     *
     * @param model
     * @return
     */
    public void checkParams(DyeTypeModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("染料类型名称不能为空");
        }
        if (StringUtil.isBlank(model.getAlias())) {
            throw new BusinessException("染料类型别名不能为空");
        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("染料类型描述不能为空");
//        }

    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(DyeTypeModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        DyeType dyeType = dyeTypeDao.find(model.getId());
        if (null == dyeType) {
            throw new BusinessException("该对象不存在！");
        }
        model.setAlias(model.getAlias());
        model.setName(model.getName());
        model.setDescription(model.getDescription());
    }


}
