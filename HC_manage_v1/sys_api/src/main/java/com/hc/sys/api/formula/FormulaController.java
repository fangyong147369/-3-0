package com.hc.sys.api.formula;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.FormulaModel;
import com.hc.sys.core.material.service.FormulaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description:配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:28
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.FORMULA)
public class FormulaController extends BaseController<FormulaModel> {
    @Resource
    private FormulaService formulaService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(FormulaModel model) throws BusinessException {
        return formulaService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return formulaService.findList();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(FormulaModel model) throws BusinessException {
        return formulaService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(FormulaModel model) throws BusinessException {
        return formulaService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(FormulaModel model) throws BusinessException {
        return formulaService.getById(model);
    }
}
