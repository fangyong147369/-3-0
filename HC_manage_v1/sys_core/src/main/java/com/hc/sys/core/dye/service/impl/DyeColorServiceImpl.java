package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeColorDao;
import com.hc.sys.core.dye.entity.DyeColor;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeColorModel;
import com.hc.sys.core.dye.service.DyeColorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染料颜色
 * @Author: fangyong
 * @CreateDate: 2018/11/5 12:12
 * @Version: 1.0.0.0
 */
@Service
public class DyeColorServiceImpl implements DyeColorService {
    @Resource
    public DyeColorDao dyeColorDao;
    @Override
    @Transactional
    public Result list(DyeColorModel model) {
        PageDataList<DyeColor> pageDataList = dyeColorDao.list(model);
        PageDataList< DyeColorModel> pageDataList_ = new PageDataList<DyeColorModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeColorModel> list = new ArrayList<DyeColorModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (DyeColor dyeColor : pageDataList.getList()) {
                DyeColorModel model_ = DyeColorModel.instance(dyeColor);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    @Override
    @Transactional
    public List<DyeColor> findAll() {
        return dyeColorDao.findAll();
    }

    @Override
    @Transactional
    public Result add(DyeColorModel model) {
        checkAddParams(model);
        DyeColor dyeColor=dyeColorDao.save(model.prototype());
        return Result.success().setData(dyeColor);
    }

    @Override
    @Transactional
    public Result update(DyeColorModel model) {
        checkParams(model);
        setUpdateParams(model);
        DyeColor dyeColor=dyeColorDao.update(model.prototype());
        return Result.success().setData(dyeColor);
    }

    @Override
    @Transactional
    public Result getById(DyeColorModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        DyeColor dyeColor= dyeColorDao.find(model.getId());
        if (null == dyeColor) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeColor);
    }

    @Override
    public Result deleteById(DyeColorModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        DyeColor dyeColor= dyeColorDao.find(model.getId());
        if (null == dyeColor) {
            Result.error("ID参数错误！");
        }
        dyeColorDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(DyeColorModel model) {
        checkParams(model);
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkParams(DyeColorModel model) {
        if (StringUtil.isBlank(model.getName())) {
            Result.error("染料颜色名称不能为空");
        }
        if (StringUtil.isBlank(model.getAlias())) {
            Result.error("染料颜色别名不能为空");
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(DyeColorModel model) {
        model.setName(model.getName());
        model.setAlias(model.getAlias());
        model.setRgb(model.getRgb());
    }
}
