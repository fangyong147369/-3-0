package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.IlluminantrelativeenergydistributionModel;
import com.hc.sys.core.dye.service.IlluminantrelativeenergydistributionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 光源-接口
 * @Author: fangyong
 * @CreateDate: 2018/11/5 16:36
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/illuminantrelativeenergydistribution")
public class llluminantrelativeenergydistributionController extends BaseController<IlluminantrelativeenergydistributionModel> {
    @Resource
    private IlluminantrelativeenergydistributionService illuminantrelativeenergydistributionService;
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public Object findAll() throws BusinessException {
        return illuminantrelativeenergydistributionService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(IlluminantrelativeenergydistributionModel model) throws BusinessException {
        return illuminantrelativeenergydistributionService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(IlluminantrelativeenergydistributionModel model) throws BusinessException {
        return illuminantrelativeenergydistributionService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByIdAndWaveLength", method = RequestMethod.POST)
    @ResponseBody
    public Object getByIdAndWaveLength(IlluminantrelativeenergydistributionModel model) throws BusinessException {
        return illuminantrelativeenergydistributionService.getByIdAndWaveLength(model);
    }
}
