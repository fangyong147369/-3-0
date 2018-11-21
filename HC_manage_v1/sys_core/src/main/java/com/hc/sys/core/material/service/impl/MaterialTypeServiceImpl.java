package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.MaterialTypeDao;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.MaterialTypeModel;
import com.hc.sys.core.material.service.MaterialTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:42
 * @Version: 1.0.0.0
 */
@Service
public class MaterialTypeServiceImpl implements MaterialTypeService {
    @Resource
    public MaterialTypeDao materialTypeDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    public Result list(MaterialTypeModel model) {
        PageDataList<MaterialType> pageDataList = materialTypeDao.list(model);
        PageDataList<MaterialTypeModel> pageDataList_ = new PageDataList<MaterialTypeModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<MaterialTypeModel> list = new ArrayList<MaterialTypeModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (MaterialType materialType : pageDataList.getList()) {
                MaterialTypeModel model_ = MaterialTypeModel.instance(materialType);
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
    public List<MaterialType> findList() {
        return materialTypeDao.findList();
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(MaterialTypeModel model) {
        checkAddParams(model);//参数检验
        MaterialType materialTyp=materialTypeDao.save(model.prototype());
        return Result.success().setData(materialTyp);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(MaterialTypeModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        MaterialType materialTyp= materialTypeDao.update(model.prototype());
        return Result.success().setData(materialTyp);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(MaterialTypeModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        MaterialType materialType = materialTypeDao.find(model.getId());
        if (null == materialType) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(materialType);
    }

    @Override
    public Result deleteById(MaterialTypeModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        MaterialType materialType = materialTypeDao.find(model.getId());
        if (null == materialType) {
            Result.error("ID参数错误！");
        }
        materialTypeDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(MaterialTypeModel model) {
        checkUpdateParams(model);
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(MaterialTypeModel model) {
        if (model.getParentId()<0) {
            model.setParentId(0);
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("材质名称不能为空");
        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("描述不能为空");
//        }
    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(MaterialTypeModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        MaterialType materialType = materialTypeDao.find(model.getId());
        if (null == materialType) {
            throw new BusinessException("该对象不存在！");
        }
        model.setParentId(model.getParentId());
        model.setName(model.getName());
        model.setDescription(model.getDescription());
    }


}
