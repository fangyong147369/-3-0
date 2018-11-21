package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.dao.DyeDao;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.material.dao.ConcentrationDao;
import com.hc.sys.core.material.dao.FormulaDao;
import com.hc.sys.core.material.entity.Concentration;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.model.ConcentrationModel;
import com.hc.sys.core.material.model.FormulaModel;
import com.hc.sys.core.material.service.ConcentrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 20:42
 * @Version: 1.0.0.0
 */
@Service
public class ConcentrationServiceImpl implements ConcentrationService {
    @Resource
    private ConcentrationDao concentrationDao;
    @Resource
    public DyeDao dyeDao;
    @Resource
    public FormulaDao formulaDao;
    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    public Result list( ConcentrationModel model) {
        PageDataList<Concentration> pageDataList = concentrationDao.list(model);
        PageDataList<ConcentrationModel> pageDataList_ = new PageDataList< ConcentrationModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<ConcentrationModel> list = new ArrayList< ConcentrationModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Concentration concentration : pageDataList.getList()) {
                ConcentrationModel model_ = ConcentrationModel.instance(concentration);
                DyeModel dyeModel= DyeModel.instance(concentration.getDye());
                FormulaModel formulaModel=FormulaModel.instance(concentration.getFormula());
                model_.setFormulaModel(formulaModel);
                model_.setDyeModel(dyeModel);
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
    public List<Concentration> findList() {
        return concentrationDao.findList();
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(ConcentrationModel model) {
        checkAddParams(model);//参数检验
        Concentration concentration=concentrationDao.save(model.prototype());
        return Result.success().setData(concentration);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(ConcentrationModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        Concentration concentration=concentrationDao.update(model.prototype());
        return Result.success().setData(concentration);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(ConcentrationModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Concentration concentration = concentrationDao.find(model.getId());
        if (null == concentration) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(concentration);
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(ConcentrationModel model) {
        checkParams(model);
    }
    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkParams(ConcentrationModel model) {
        if(model.getDyeId()>0){
            Dye dye=dyeDao.find(model.getDyeId());
            if(null!=dye){
                model.setDye(dye);
            }
        }
        if (model.getFormulaId() > 0) {
            Formula formula = formulaDao.find(model.getFormulaId());
            if (null != formula) {
                model.setFormula(formula);
            }
        }
    }
    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(ConcentrationModel model) {
        checkParams(model);
    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(ConcentrationModel model) {
        if (model.getId() <= 0) {
            Result.error("参数错误");
        }
         Concentration concentration = concentrationDao.find(model.getId());
        if (null == concentration) {
            Result.error("该对象不存在！");
        }
        model.setConcentration(model.getConcentration());

    }


}
