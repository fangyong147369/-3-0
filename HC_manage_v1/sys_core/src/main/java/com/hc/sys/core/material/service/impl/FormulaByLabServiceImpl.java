package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.material.dao.FormulaByLabDao;
import com.hc.sys.core.material.entity.FormulaByLab;
import com.hc.sys.core.material.model.FormulaByLabModel;
import com.hc.sys.core.material.service.FormulaByLabService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
/**
 * @Description: 配方查询
 * @Author: fangyong
 * @CreateDate: 2018/11/17 16:47
 * @Version: 1.0.0.0
 */
@Service
public class FormulaByLabServiceImpl implements FormulaByLabService {
    @Resource
    private FormulaByLabDao formulaByLabDao;
    /**
     * @description 配方查询列表
     * @author: fangyong
     * @date 2018/11/17 16:50
     */
    @Override
    public List<FormulaByLab> all() {
        return formulaByLabDao.all();
    }
   /**
    * @description add
    * @author: fangyong
    * @date 2018/11/19 11:57
    */
    @Override
    @Transactional
    public Result add(FormulaByLabModel model) {
        FormulaByLab formula = formulaByLabDao.save(model.prototype());
        if (formula == null) {
            return Result.error("配方录入失败！");
        }
        return Result.success().setData(formula);
    }
   /**
    * @description update
    * @author: fangyong
    * @date 2018/11/19 11:58
    */
    @Override
    @Transactional
    public Result update(FormulaByLabModel model) {
        return null;
    }
    /**
     * @description getById
     * @author: fangyong
     * @date 2018/11/19 11:58
     */
    @Override
    public Result getById(FormulaByLabModel model) {
        return null;
    }
    /**
     * @description deleteById
     * @author: fangyong
     * @date 2018/11/19 11:58
     */
    @Override
    public Result deleteById(FormulaByLabModel model) {
        return null;
    }
}
