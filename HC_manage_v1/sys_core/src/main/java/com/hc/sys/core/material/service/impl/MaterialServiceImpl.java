package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.ColorantDao;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.manage.dao.OperatorDao;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.dao.MaterialDao;
import com.hc.sys.core.material.dao.MaterialTypeDao;
import com.hc.sys.core.material.entity.Material;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.MaterialModel;
import com.hc.sys.core.material.model.MaterialTypeModel;
import com.hc.sys.core.material.service.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 材质
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:42
 * @Version: 1.0.0.0
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Resource
    private MaterialDao materialDao;
    @Resource
    public MaterialTypeDao materialTypeDao;
    @Resource
    public OperatorDao operatorDao;
    @Resource
    public ColorantDao colorantDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public Result list(MaterialModel model) {
        PageDataList<Material> pageDataList = materialDao.list(model);
        PageDataList<MaterialModel> pageDataList_ = new PageDataList<MaterialModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<MaterialModel> list = new ArrayList<MaterialModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Material material : pageDataList.getList()) {
                MaterialModel model_ = MaterialModel.instance(material);
                OperatorModel operatorModel = OperatorModel.instance(material.getOperator());
                model_.setOperatorModel(operatorModel);
                ColorantModel colorantModel = ColorantModel.instance(material.getColorant());
                model_.setColorantModel(colorantModel);
                MaterialTypeModel materialTypeModel = MaterialTypeModel.instance(material.getMaterialType());
                model_.setMaterialTypeModel(materialTypeModel);
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
    public List<MaterialModel> findList() {
        List<MaterialModel> modelList = new ArrayList<MaterialModel>();
        List<Material> list = materialDao.findList();
        if (list != null && list.size() > 0) {
            for (Material material :list) {
                MaterialModel model_ = MaterialModel.instance(material);
                MaterialTypeModel materialTypeModel = MaterialTypeModel.instance(material.getMaterialType());
                model_.setMaterialTypeModel(materialTypeModel);
                modelList.add(model_);
            }
        }
        return modelList;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(MaterialModel model) {
        checkAddParams(model);//参数检验
        Material material = materialDao.save(model.prototype());
        return Result.success().setData(material);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(MaterialModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        Material material = materialDao.update(model.prototype());
        return Result.success().setData(material);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(MaterialModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Material material = materialDao.find(model.getId());
        if (null == material) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(material);
    }

    @Override
    public Result deleteById(MaterialModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        Material material = materialDao.find(model.getId());
        if (null == material) {
            Result.error("ID参数错误！");
        }
        materialDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(MaterialModel model) {
        checkUpdateParams(model);
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(MaterialModel model) {
//        if (model.getCloudId()<0) {
//            throw new BusinessException("云端id错误");
//        }
//        if (model.getStatus()<0) {
//            throw new BusinessException("云端状态错误");
//        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("描述不能为空");
//        }
        if (StringUtil.isBlank(model.getAlias())) {
            Result.error("材质别名不能为空！");
        }
        if (StringUtil.isBlank(model.getName())) {
            Result.error("材质名称不能为空");
        }
        if (model.getOperatorId() > 0) {
            Operator operator = operatorDao.find(model.getOperatorId());
            if (null != operator) {
                model.setOperator(operator);
            }
        }
        if (model.getColorantId() > 0) {
            Colorant colorant = colorantDao.find(model.getColorantId());
            if (null != colorant) {
                model.setColorant(colorant);
            }
        }
        if (model.getMaterialTypeId() > 0) {
            MaterialType materialType = materialTypeDao.find(model.getMaterialTypeId());
            if (null != materialType) {
                model.setMaterialType(materialType);
            }
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(MaterialModel model) {
        if (model.getId() <= 0) {
            Result.error("参数错误");
        }
        Material material = materialDao.find(model.getId());
        if (null == material) {
            Result.error("该对象不存在！");
        }
        model.setOperator(material.getOperator());
        model.setAddTime(material.getAddTime());
        model.setCloudId(model.getCloudId());
        model.setStatus(model.getStatus());
        model.setDescription(model.getDescription());
        model.setLiquorRatio(model.getLiquorRatio());
        model.setAlias(model.getAlias());
        model.setWeight(model.getWeight());
        model.setDipDyeing(model.getDipDyeing());
        model.setName(model.getName());
    }


}
