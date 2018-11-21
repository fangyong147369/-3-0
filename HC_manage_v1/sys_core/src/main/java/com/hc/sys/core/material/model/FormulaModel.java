package com.hc.sys.core.material.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.dye.model.IlluminantModel;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.entity.Formula;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:41
 * @Version: 1.0.0.0
 */
public class FormulaModel extends Formula {
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
     * materialModel
     **/
    private long materialId;
    /**
     * colorantModel
     **/
    private long colorantId;
    /**
     * dyeGroupModel
     **/
    private long dyeGroupId;
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
    public static FormulaModel instance(Formula material) {
        if (material == null || material.getId() < 0) {
            return null;
        }
        FormulaModel formulaModel = new FormulaModel();
        BeanUtils.copyProperties(material, formulaModel);
        return formulaModel;
    }

    /**
     * model转换实体
     */
    public Formula prototype() {
        Formula formula = new Formula();
        BeanUtils.copyProperties(this, formula);
        return formula;
    }


    /**
     * 获取 当前页码
     *
     * @return pageNo 当前页码
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 设置 当前页码
     *
     * @param pageNo 当前页码
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取 每页数据条数
     *
     * @return pageSize 每页数据条数
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置 每页数据条数
     *
     * @param pageSize 每页数据条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取 条件查询
     *
     * @return searchName 条件查询
     */
    public String getSearchName() {
        return this.searchName;
    }

    /**
     * 设置 条件查询
     *
     * @param searchName 条件查询
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }


    /**
     * 获取 operatorModel
     *
     * @return operatorModel operatorModel
     */
    public OperatorModel getOperatorModel() {
        return this.operatorModel;
    }

    /**
     * 设置 operatorModel
     *
     * @param operatorModel operatorModel
     */
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取 materialModel
     *
     * @return materialModel materialModel
     */
    public MaterialModel getMaterialModel() {
        return this.materialModel;
    }

    /**
     * 设置 materialModel
     *
     * @param materialModel materialModel
     */
    public void setMaterialModel(MaterialModel materialModel) {
        this.materialModel = materialModel;
    }

    /**
     * 获取 colorantModel
     *
     * @return colorantModel colorantModel
     */
    public ColorantModel getColorantModel() {
        return this.colorantModel;
    }

    /**
     * 设置 colorantModel
     *
     * @param colorantModel colorantModel
     */
    public void setColorantModel(ColorantModel colorantModel) {
        this.colorantModel = colorantModel;
    }

    /**
     * 获取 dyeGroupModel
     *
     * @return dyeGroupModel dyeGroupModel
     */
    public DyeGroupModel getDyeGroupModel() {
        return this.dyeGroupModel;
    }

    /**
     * 设置 dyeGroupModel
     *
     * @param dyeGroupModel dyeGroupModel
     */
    public void setDyeGroupModel(DyeGroupModel dyeGroupModel) {
        this.dyeGroupModel = dyeGroupModel;
    }

    /**
     * 获取 materialModel
     *
     * @return technologyModel materialModel
     */
    public TechnologyModel getTechnologyModel() {
        return this.technologyModel;
    }

    /**
     * 设置 materialModel
     *
     * @param technologyModel materialModel
     */
    public void setTechnologyModel(TechnologyModel technologyModel) {
        this.technologyModel = technologyModel;
    }


    /**
     * 获取 operatorId
     *
     * @return operatorId operatorId
     */
    public long getOperatorId() {
        return this.operatorId;
    }

    /**
     * 设置 operatorId
     *
     * @param operatorId operatorId
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取 materialModel
     *
     * @return materialId materialModel
     */
    public long getMaterialId() {
        return this.materialId;
    }

    /**
     * 设置 materialModel
     *
     * @param materialId materialModel
     */
    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取 colorantModel
     *
     * @return colorantId colorantModel
     */
    public long getColorantId() {
        return this.colorantId;
    }

    /**
     * 设置 colorantModel
     *
     * @param colorantId colorantModel
     */
    public void setColorantId(long colorantId) {
        this.colorantId = colorantId;
    }

    /**
     * 获取 dyeGroupModel
     *
     * @return dyeGroupId dyeGroupModel
     */
    public long getDyeGroupId() {
        return this.dyeGroupId;
    }

    /**
     * 设置 dyeGroupModel
     *
     * @param dyeGroupId dyeGroupModel
     */
    public void setDyeGroupId(long dyeGroupId) {
        this.dyeGroupId = dyeGroupId;
    }

    /**
     * 获取 materialModel
     *
     * @return technologyId materialModel
     */
    public long getTechnologyId() {
        return this.technologyId;
    }

    /**
     * 设置 materialModel
     *
     * @param technologyId materialModel
     */
    public void setTechnologyId(long technologyId) {
        this.technologyId = technologyId;
    }

    /**
     * 获取 concentrationModel
     *
     * @return concentrationId concentrationModel
     */
    public long getConcentrationId() {
        return this.concentrationId;
    }

    /**
     * 设置 concentrationModel
     *
     * @param concentrationId concentrationModel
     */
    public void setConcentrationId(long concentrationId) {
        this.concentrationId = concentrationId;
    }

    /**
     * 获取 concentrationModel
     *
     * @return concentrationModel concentrationModel
     */
    public List<ConcentrationModel> getConcentrationModel() {
        return this.concentrationModel;
    }

    /**
     * 设置 concentrationModel
     *
     * @param concentrationModel concentrationModel
     */
    public void setConcentrationModel(List<ConcentrationModel> concentrationModel) {
        this.concentrationModel = concentrationModel;
    }

    public int getFormulaCount() {
        return formulaCount;
    }

    public void setFormulaCount(int formulaCount) {
        this.formulaCount = formulaCount;
    }
}
