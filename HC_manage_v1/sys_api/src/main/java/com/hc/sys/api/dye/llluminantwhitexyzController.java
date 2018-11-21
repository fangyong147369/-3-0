package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.model.IlluminantwhitexyzModel;
import com.hc.sys.core.dye.service.IlluminantwhitexyzService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
/**
 * @Description: 光源-接口
 * @Author: fangyong
 * @CreateDate: 2018/11/5 16:11
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/illuminantwhitexyz")
public class llluminantwhitexyzController extends BaseController<IlluminantwhitexyzModel> {
    @Resource
    private IlluminantwhitexyzService illuminantwhitexyzService;
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public Object findAll() throws BusinessException {
        return illuminantwhitexyzService.findAll();
    }
    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(IlluminantwhitexyzModel model) throws BusinessException {
        return illuminantwhitexyzService.add(model);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(IlluminantwhitexyzModel model) throws BusinessException {
        return illuminantwhitexyzService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByIdAndAngle", method = RequestMethod.POST)
    @ResponseBody
    public Object getByIdAndAngle(IlluminantwhitexyzModel model) throws BusinessException {
        return illuminantwhitexyzService.getByIdAndAngle(model);
    }
}
