package com.hc.sys.core.material.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.entity.FormulaByLab;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 配方查询
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:41
 * @Version: 1.0.0.0
 */
public class FormulaByLabModel extends FormulaByLab {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * operatorModel
     **/
    private OperatorModel operatorModel;
    /**
     * materialModel
     **/
    private MaterialModel materialModel;
    /**
     * colorantModel
     **/
    private ColorantModel colorantModel;
    /**
     * dyeGroupModel
     **/
    private DyeGroupModel dyeGroupModel;
    /**
     * materialModel
     **/
    private TechnologyModel technologyModel;
    /**
     * concentrationModel
     **/
    private List<ConcentrationModel> concentrationModel;
    /**
     * operatorId
     **/
    private long operatorId;

    /**
     * colorantModel
     **/
    private long colorantId;

    /**
     * materialModel
     **/
    private long technologyId;
    /**
     * concentrationModel
     **/
    private long concentrationId;
    /**
     * 配方总条数
     **/
    private int formulaCount;

    /**
     * 实体转换model
     */
    public static FormulaByLabModel instance(FormulaByLab formulaByLab) {
        if (formulaByLab == null) {
            return null;
        }
        FormulaByLabModel formulaModel = new FormulaByLabModel();
        BeanUtils.copyProperties(formulaByLab, formulaModel);
        return formulaModel;
    }

    /**
     * model转换实体
     */
    public FormulaByLab prototype() {
        FormulaByLab formulaByLab = new FormulaByLab();
        BeanUtils.copyProperties(this, formulaByLab);
        return formulaByLab;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public MaterialModel getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(MaterialModel materialModel) {
        this.materialModel = materialModel;
    }

    public ColorantModel getColorantModel() {
        return colorantModel;
    }

    public void setColorantModel(ColorantModel colorantModel) {
        this.colorantModel = colorantModel;
    }

    public DyeGroupModel getDyeGroupModel() {
        return dyeGroupModel;
    }

    public void setDyeGroupModel(DyeGroupModel dyeGroupModel) {
        this.dyeGroupModel = dyeGroupModel;
    }

    public TechnologyModel getTechnologyModel() {
        return technologyModel;
    }

    public void setTechnologyModel(TechnologyModel technologyModel) {
        this.technologyModel = technologyModel;
    }

    public List<ConcentrationModel> getConcentrationModel() {
        return concentrationModel;
    }

    public void setConcentrationModel(List<ConcentrationModel> concentrationModel) {
        this.concentrationModel = concentrationModel;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public long getColorantId() {
        return colorantId;
    }

    public void setColorantId(long colorantId) {
        this.colorantId = colorantId;
    }


    public long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(long technologyId) {
        this.technologyId = technologyId;
    }

    public long getConcentrationId() {
        return concentrationId;
    }

    public void setConcentrationId(long concentrationId) {
        this.concentrationId = concentrationId;
    }

    public int getFormulaCount() {
        return formulaCount;
    }

    public void setFormulaCount(int formulaCount) {
        this.formulaCount = formulaCount;
    }
}
