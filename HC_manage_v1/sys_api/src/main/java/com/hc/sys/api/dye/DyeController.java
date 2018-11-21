package com.hc.sys.api.dye;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.service.DyeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:28
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dye")
public class DyeController extends BaseController<DyeModel> {
    @Resource
    private DyeService dyeService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(DyeModel model) throws BusinessException {
        return dyeService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return dyeService.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeModel model) throws BusinessException {
        return dyeService.add(model);
    }
    /**
     * 批量添加
     * @param dyes
     * @return
     */
    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    @ResponseBody
    public Object addList(List<Dye> dyes) throws BusinessException {
        return dyeService.addList(dyes);
    }
    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeModel model) throws BusinessException {
        return dyeService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeModel model) throws BusinessException {
        return dyeService.getById(model);
    }
}
