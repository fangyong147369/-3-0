package com.hc.sys.core.material.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.material.entity.Concentration;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:32
 * @Version: 1.0.0.0
 */
public class ConcentrationModel  extends Concentration {
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
     * dyeModel
     **/
    private DyeModel dyeModel;
    /**
     * FormulaModel
     **/
    private FormulaModel formulaModel;

    /**
     * dyeId
     **/
    private long dyeId;
    /**
     * formulaId
     **/
    private long formulaId;

    /**
     * 实体转换model
     */
    public static ConcentrationModel instance(Concentration concentration) {
        if(concentration==null||concentration.getId()<0){
            return null;
        }
        ConcentrationModel concentrationModel = new ConcentrationModel();
        BeanUtils.copyProperties(concentration, concentrationModel);
        return concentrationModel;
    }

    /**
     * model转换实体
     */
    public Concentration prototype() {
        Concentration concentration = new Concentration();
        BeanUtils.copyProperties(this, concentration);
        return concentration;
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
     * 获取 IlluminantModel
     *
     * @return dyeModel IlluminantModel
     */
    public DyeModel getDyeModel() {
        return this.dyeModel;
    }

    /**
     * 设置 IlluminantModel
     *
     * @param dyeModel IlluminantModel
     */
    public void setDyeModel(DyeModel dyeModel) {
        this.dyeModel = dyeModel;
    }

    /**
     * 获取 dyeId
     *
     * @return dyeId dyeId
     */
    public long getDyeId() {
        return this.dyeId;
    }

    /**
     * 设置 dyeId
     *
     * @param dyeId dyeId
     */
    public void setDyeId(long dyeId) {
        this.dyeId = dyeId;
    }

    /**
     * 获取 formulaId
     *
     * @return formulaId formulaId
     */
    public long getFormulaId() {
        return this.formulaId;
    }

    /**
     * 设置 formulaId
     *
     * @param formulaId formulaId
     */
    public void setFormulaId(long formulaId) {
        this.formulaId = formulaId;
    }

    /**
     * 获取 FormulaModel
     *
     * @return formulaModel FormulaModel
     */
    public FormulaModel getFormulaModel() {
        return this.formulaModel;
    }

    /**
     * 设置 FormulaModel
     *
     * @param formulaModel FormulaModel
     */
    public void setFormulaModel(FormulaModel formulaModel) {
        this.formulaModel = formulaModel;
    }
}
