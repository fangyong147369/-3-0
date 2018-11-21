package com.hc.sys.api.formula;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.constant.BaseControllerUrl;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.model.MaterialModel;
import com.hc.sys.core.material.model.MaterialProofingModel;
import com.hc.sys.core.material.service.MaterialProofingService;
import com.hc.sys.core.material.service.MaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/10/20 10:00
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping(BaseControllerUrl.MATERIALPROOFING)
public class MaterialProofingController extends BaseController<MaterialProofingModel> {
    @Resource
    private MaterialProofingService materialProofingService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(MaterialProofingModel model) throws BusinessException {
        return materialProofingService.list(model);
    }
//    /**
//     * 无分页列表
//     * @return
//     */
//    @RequestMapping(value = "/all", method = RequestMethod.POST)
//    @ResponseBody
//    public Object all() throws BusinessException {
//        return materialProofingService.findList();
//    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(MaterialProofingModel model) throws BusinessException {
        return materialProofingService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(MaterialProofingModel model) throws BusinessException {
        return materialProofingService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(MaterialProofingModel model) throws BusinessException {
        return materialProofingService.getById(model);
    }


}
