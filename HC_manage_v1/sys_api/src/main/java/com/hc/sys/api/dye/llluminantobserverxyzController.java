package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import com.hc.sys.core.dye.service.IlluminantobserverxyzService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 光源-接口
 * @Author: fangyong
 * @CreateDate: 2018/11/5 16:21
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/illuminantobserverxyz")
public class llluminantobserverxyzController extends BaseController<IlluminantobserverxyzModel> {
    @Resource
    private IlluminantobserverxyzService illuminantobserverxyzService;
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public Object findAll() throws BusinessException {
        return illuminantobserverxyzService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(IlluminantobserverxyzModel model) throws BusinessException {
        return illuminantobserverxyzService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(IlluminantobserverxyzModel model) throws BusinessException {
        return illuminantobserverxyzService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByWaveLengthAndAngle", method = RequestMethod.POST)
    @ResponseBody
    public Object getByWaveLengthAndAngle(IlluminantobserverxyzModel model) throws BusinessException {
        return illuminantobserverxyzService.getByWaveLengthAndAngle(model);
    }
}
