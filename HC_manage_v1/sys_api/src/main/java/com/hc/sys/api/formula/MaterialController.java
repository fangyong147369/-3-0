package com.hc.sys.api.formula;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.MaterialModel;
import com.hc.sys.core.material.service.MaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/10/20 10:00
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.MATERIAL)
public class MaterialController extends BaseController<MaterialModel> {
    @Resource
    private MaterialService materialService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(MaterialModel model) throws BusinessException {
        return materialService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return materialService.findList();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(MaterialModel model) throws BusinessException {
        return materialService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(MaterialModel model) throws BusinessException {
        return materialService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(MaterialModel model) throws BusinessException {
        return materialService.getById(model);
    }


}
