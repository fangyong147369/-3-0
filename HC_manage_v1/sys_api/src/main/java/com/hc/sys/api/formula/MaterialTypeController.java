package com.hc.sys.api.formula;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.MaterialTypeModel;
import com.hc.sys.core.material.service.MaterialTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/20 10:12
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.MATERIALTYPE)
public class MaterialTypeController extends BaseController<MaterialTypeModel> {
    @Resource
    private MaterialTypeService materialTypeService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(MaterialTypeModel model) throws BusinessException {
        return materialTypeService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return materialTypeService.findList();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(MaterialTypeModel model) throws BusinessException {
        return materialTypeService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(MaterialTypeModel model) throws BusinessException {
        return materialTypeService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(MaterialTypeModel model) throws BusinessException {
        return materialTypeService.getById(model);
    }
}
