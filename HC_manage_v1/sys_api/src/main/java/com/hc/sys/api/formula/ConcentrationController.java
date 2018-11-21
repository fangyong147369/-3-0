package com.hc.sys.api.formula;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.ConcentrationModel;
import com.hc.sys.core.material.service.ConcentrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/10/20 9:56
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.CONCENTRATION)
public class ConcentrationController extends BaseController<ConcentrationModel> {
    @Resource
    private ConcentrationService concentrationService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(ConcentrationModel model) throws BusinessException {
        return concentrationService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return concentrationService.findList();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ConcentrationModel model) throws BusinessException {
        return concentrationService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ConcentrationModel model) throws BusinessException {
        return concentrationService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(ConcentrationModel model) throws BusinessException {
        return concentrationService.getById(model);
    }

}
