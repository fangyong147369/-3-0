package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.service.ColorantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Description: 颜色-接口
 * @Author: fangyong
 * @CreateDate: 2018/10/18 15:00
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/colorant")
public class ColorantController extends BaseController<ColorantModel> {
    @Resource
    private ColorantService colorantService;
    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(ColorantModel model) throws BusinessException {
        return colorantService.list(model);
    }
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public Object findAll() throws BusinessException {
        return colorantService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ColorantModel model) throws BusinessException {
        return colorantService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ColorantModel model) throws BusinessException {
        return colorantService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(ColorantModel model) throws BusinessException {
        return colorantService.getById(model);
    }
}
