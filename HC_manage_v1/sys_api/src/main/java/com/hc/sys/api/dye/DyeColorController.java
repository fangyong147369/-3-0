package com.hc.sys.api.dye;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.entity.DyeColor;
import com.hc.sys.core.dye.model.DyeColorModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.service.DyeColorService;
import com.hc.sys.core.dye.service.DyeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:染料颜色
 * @Author: fangyong
 * @CreateDate: 2018/10/19 15:28
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/dye/dyecolor")
public class DyeColorController extends BaseController<DyeColorModel> {
    @Resource
    private DyeColorService dyeColorService;
    /**
     * 分页列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(DyeColorModel model) throws BusinessException {
        return dyeColorService.list(model);
    }
    /**
     * 无分页列表
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return dyeColorService.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DyeColorModel model) throws BusinessException {
        return dyeColorService.add(model);
    }
    /**
     * 修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DyeColorModel model) throws BusinessException {
        return dyeColorService.update(model);
    }

    /**
     * 获取
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DyeColorModel model) throws BusinessException {
        return dyeColorService.getById(model);
    }
}
