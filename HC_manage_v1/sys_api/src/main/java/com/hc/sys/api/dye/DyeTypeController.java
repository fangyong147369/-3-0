package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.DyeTypeModel;
import com.hc.sys.core.dye.service.DyeTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description:染料类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:38
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dyetype")
public class DyeTypeController extends BaseController<DyeTypeModel> {
    @Resource
    private DyeTypeService dyeTypeService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(DyeTypeModel model) throws BusinessException {
        return dyeTypeService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Object all() throws BusinessException {
        return dyeTypeService.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeTypeModel model) throws BusinessException {
        return dyeTypeService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeTypeModel model) throws BusinessException {
        return dyeTypeService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeTypeModel model) throws BusinessException {
        return dyeTypeService.getById(model);
    }
}
