package com.hc.sys.api.formula;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.TechnologyModel;
import com.hc.sys.core.material.service.TechnologyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/20 10:03
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.TECHNOLOGY)
public class TechnologyController extends BaseController<TechnologyModel>{
    @Resource
    private TechnologyService technologyService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(TechnologyModel model) throws BusinessException {
        return technologyService.list(model);
    }

    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return technologyService.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(TechnologyModel model) throws BusinessException {
        return technologyService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(TechnologyModel model) throws BusinessException {
        return technologyService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(TechnologyModel model) throws BusinessException {
        return technologyService.getById(model);
    }
}
