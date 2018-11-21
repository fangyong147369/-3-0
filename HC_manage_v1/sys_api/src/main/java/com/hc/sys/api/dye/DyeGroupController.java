package com.hc.sys.api.dye;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.dye.service.DyeGroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description:染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:48
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dyegroup")
public class DyeGroupController extends BaseController<DyeGroupModel> {
    @Resource
    private DyeGroupService dyeGroupService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(DyeGroupModel model) throws BusinessException {
        return dyeGroupService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Object all() throws BusinessException {
        return dyeGroupService.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeGroupModel model) throws BusinessException {
        return dyeGroupService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeGroupModel model) throws BusinessException {
        return dyeGroupService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeGroupModel model) throws BusinessException {
        return dyeGroupService.getById(model);
    }
}
