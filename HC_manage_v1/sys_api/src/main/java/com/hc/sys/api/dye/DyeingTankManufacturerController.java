package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import com.hc.sys.core.dye.service.DyeingTankManufacturerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 染缸厂商
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:39
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dyeingTankManufacturer")
public class DyeingTankManufacturerController extends BaseController<DyeingTankManufacturerModel> {
    @Resource
    private DyeingTankManufacturerService dyeingTankManufacturerService;
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(DyeingTankManufacturerModel model) throws BusinessException {
        return dyeingTankManufacturerService.list(model);
    }
    /**
     * 无分页列表
     * @param
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object findList() throws BusinessException {
        return dyeingTankManufacturerService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeingTankManufacturerModel model) throws BusinessException {
        return dyeingTankManufacturerService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeingTankManufacturerModel model) throws BusinessException {
        return dyeingTankManufacturerService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeingTankManufacturerModel model) throws BusinessException {
        return dyeingTankManufacturerService.getById(model);
    }
}
