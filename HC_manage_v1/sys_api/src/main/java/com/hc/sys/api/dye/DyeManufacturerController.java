package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.DyeManufacturerModel;
import com.hc.sys.core.dye.service.DyeManufacturerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description:染料厂商
 * @Author: fangyong
 * @CreateDate: 2018/10/19 14:23
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dyemanufacturer")
public class DyeManufacturerController  extends BaseController<DyeManufacturerModel> {
    @Resource
    private DyeManufacturerService dyeManufacturerService;
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(DyeManufacturerModel model) throws BusinessException {
        return dyeManufacturerService.list(model);
    }
    /**
     * 无分页列表
     * @param
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object findList() throws BusinessException {
        return dyeManufacturerService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeManufacturerModel model) throws BusinessException {
        return dyeManufacturerService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeManufacturerModel model) throws BusinessException {
        return dyeManufacturerService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeManufacturerModel model) throws BusinessException {
        return dyeManufacturerService.getById(model);
    }
}
