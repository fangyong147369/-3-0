package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.ColorantDao;
import com.hc.sys.core.dye.dao.DyeGroupDao;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.manage.dao.OperatorDao;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.dao.*;
import com.hc.sys.core.material.entity.*;
import com.hc.sys.core.material.model.*;
import com.hc.sys.core.material.service.FormulaService;
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
public class FormulaServiceImpl implements FormulaService {
    @Resource
    private FormulaDao formulaDao;
    @Resource
    public MaterialDao materialDao;
    @Resource
    public OperatorDao operatorDao;
    @Resource
    public ColorantDao colorantDao;
    @Resource
    public DyeGroupDao dyeGroupDao;
    @Resource
    public TechnologyDao technologyDao;
    @Resource
    public ConcentrationDao concentrationDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public Result list(FormulaModel model) {
        PageDataList<Formula> pageDataList = formulaDao.list(model);
        PageDataList<FormulaModel> pageDataList_ = new PageDataList<FormulaModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<FormulaModel> list = new ArrayList<FormulaModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Formula formula : pageDataList.getList()) {
                FormulaModel model_ = FormulaModel.instance(formula);
                if (formula.getOperator() != null) {
                    OperatorModel operatorModel = OperatorModel.instance(formula.getOperator());
                    model_.setOperatorModel(operatorModel);
                }
                if (formula.getColorant() != null) {
                    ColorantModel colorantModel = ColorantModel.instance(formula.getColorant());
                    model_.setColorantModel(colorantModel);
                }
                if (formula.getMaterial() != null) {
                    MaterialModel materialModel = MaterialModel.instance(formula.getMaterial());
                    model_.setMaterialModel(materialModel);
                }
                if (formula.getDyeGroup() != null) {
                    DyeGroupModel dyeGroupModel = DyeGroupModel.instance(formula.getDyeGroup());
                    model_.setDyeGroupModel(dyeGroupModel);
                }
                if (formula.getTechnology() != null) {
                    TechnologyModel technologyModel = TechnologyModel.instance(formula.getTechnology());
                    model_.setTechnologyModel(technologyModel);
                }
                List<Concentration> concentrationList = concentrationDao.findByProperty("formula.id", formula.getId());
                List modeList = new ArrayList();
                if (concentrationList != null && concentrationList.size() > 0) {
                    for (Concentration concentration : concentrationList) {
                        ConcentrationModel concentrationModel_ = ConcentrationModel.instance(concentration);
                        concentrationModel_.setDyeModel(DyeModel.instance(concentration.getDye()));
                        modeList.add(concentrationModel_);
                    }
                }
                model_.setConcentrationModel(modeList);
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
    public List<FormulaModel> findList() {
        List<FormulaModel> modelList=new ArrayList<>();
        List<Formula> formulaList = formulaDao.findList();
        if (formulaList != null && formulaList.size() > 0) {
            for (Formula formula : formulaList) {
                FormulaModel model=FormulaModel.instance(formula);
                model.setFormulaCount(formulaList.size());
                if(formula.getColorant()!=null){
                    model.setColorantModel(ColorantModel.instance(formula.getColorant()));
                }
                if(formula.getDyeGroup()!=null){
                    model.setDyeGroupModel(DyeGroupModel.instance(formula.getDyeGroup()));
                }
                if(formula.getMaterial()!=null){
                    model.setMaterialModel(MaterialModel.instance(formula.getMaterial()));
                }
                modelList.add(model);
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
    public Result add(FormulaModel model) {
        checkAddParams(model);//参数检验
        Formula formula = formulaDao.save(model.prototype());
        if (formula == null) {
            return Result.error("配方录入失败！");
        }
        return Result.success().setData(formula);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(FormulaModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        Formula formula = formulaDao.update(model.prototype());
        return Result.success().setData(formula);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(FormulaModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Formula formula = formulaDao.find(model.getId());
        if (null == formula) {
            return Result.success("该对象不存在！");
        }
        FormulaModel model_ = FormulaModel.instance(formula);
        OperatorModel operatorModel = OperatorModel.instance(formula.getOperator());
        model_.setOperatorModel(operatorModel);
        ColorantModel colorantModel = ColorantModel.instance(formula.getColorant());
        model_.setColorantModel(colorantModel);
        MaterialModel materialModel = MaterialModel.instance(formula.getMaterial());
        model_.setMaterialModel(materialModel);
        DyeGroupModel dyeGroupModel = DyeGroupModel.instance(formula.getDyeGroup());
        model_.setDyeGroupModel(dyeGroupModel);
        TechnologyModel technologyModel = TechnologyModel.instance(formula.getTechnology());
        model_.setTechnologyModel(technologyModel);
        return Result.success().setData(model_);
    }

    @Override
    public boolean getByName(String name) {
      boolean f=false;
        Formula formulaCheck = formulaDao.findObjByProperty("name", name);
        if (formulaCheck != null) {
            f=true;
        }
        return f;
    }

    @Override
    public Result deleteById(FormulaModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        Formula formula = formulaDao.find(model.getId());
        if (null == formula) {
            Result.error("此记录不存在！");
        }
        formulaDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(FormulaModel model) {
        checkUpdateParams(model);
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(FormulaModel model) {
        if (model.getStatus() < 0) {
            throw new BusinessException("云端状态错误");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空");
        }
        if (model.getType() < 0) {
            throw new BusinessException("配方类型错误：目前只支持0或1");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("材质名称不能为空");
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
        if (model.getMaterialId() > 0) {
            Material material = materialDao.find(model.getMaterialId());
            if (null != material) {
                model.setMaterial(material);
            }
        }
        if (model.getDyeGroupId() > 0) {
            DyeGroup dyeGroup = dyeGroupDao.find(model.getDyeGroupId());
            if (null != dyeGroup) {
                model.setDyeGroup(dyeGroup);
            }
        }

        if (model.getTechnologyId() > 0) {
            Technology technology = technologyDao.find(model.getTechnologyId());
            if (null != technology) {
                model.setTechnology(technology);
            }
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(FormulaModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        model.setSampleName(model.getSampleName());
        model.setStatus(model.getStatus());
        model.setType(model.getType());
        model.setName(model.getName());
    }


}
