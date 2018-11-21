package com.hc.sys.core.material.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.core.material.entity.FormulaByLab;
import java.util.List;
/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/11/17 15:43
 * @Version: 1.0.0.0
 */
public interface FormulaByLabDao extends BaseDao<FormulaByLab> {
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<FormulaByLab> all();
}
