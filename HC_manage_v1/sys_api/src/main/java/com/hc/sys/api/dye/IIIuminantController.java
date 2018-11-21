package com.hc.sys.api.dye;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.IlluminantModel;
import com.hc.sys.core.dye.service.IlluminantService;
import com.hc.sys.core.manage.model.MenuModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 光源-接口
 * @Author: fangyong
 * @CreateDate: 2018/10/18 15:00
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/illuminant")
public class IIIuminantController extends BaseController<IlluminantModel> {
    @Resource
    private IlluminantService illuminantService;
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public Object findAll() throws BusinessException {
        return illuminantService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(IlluminantModel model) throws BusinessException {
        return illuminantService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(IlluminantModel model) throws BusinessException {
        return illuminantService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByIdAndAngle", method = RequestMethod.POST)
    @ResponseBody
    public Object getByIdAndAngle(IlluminantModel model) throws BusinessException {
        return illuminantService.getByIdAndAngle(model);
    }
}
