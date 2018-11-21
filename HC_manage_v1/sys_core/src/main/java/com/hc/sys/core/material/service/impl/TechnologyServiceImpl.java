package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeingTankDao;
import com.hc.sys.core.dye.entity.DyeingTank;
import com.hc.sys.core.dye.model.DyeingTankModel;
import com.hc.sys.core.material.dao.FactoryDao;
import com.hc.sys.core.material.dao.TechnologyDao;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.entity.Technology;
import com.hc.sys.core.material.model.FactoryModel;
import com.hc.sys.core.material.model.TechnologyModel;
import com.hc.sys.core.material.service.TechnologyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 20:48
 * @Version: 1.0.0.0
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Resource
    private TechnologyDao technologyDao;
    @Resource
    public DyeingTankDao dyeingTankDao;
    @Resource
    public FactoryDao factoryDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    public Result list(TechnologyModel model) {
        PageDataList<Technology> pageDataList = technologyDao.list(model);
        PageDataList<TechnologyModel> pageDataList_ = new PageDataList<TechnologyModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<TechnologyModel> list = new ArrayList<TechnologyModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Technology technology : pageDataList.getList()) {
                TechnologyModel model_ = TechnologyModel.instance(technology);
                DyeingTankModel dyeManufacturerModel = DyeingTankModel.instance(technology.getDyeingTank());
                model_.setDyeingTankModel(dyeManufacturerModel);
                FactoryModel factoryModel = FactoryModel.instance(technology.getFactory());
                model_.setFactoryModel(factoryModel);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 无分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public List<TechnologyModel> findAll() {
        List<Technology> technologyList=technologyDao.findAll();
        List<TechnologyModel> technologyModelList=new ArrayList<TechnologyModel>();
        for(Technology technology:technologyList){
            TechnologyModel technologyModel=TechnologyModel.instance(technology);
            FactoryModel factoryModel=FactoryModel.instance(technology.getFactory());
            technologyModel.setFactoryModel(factoryModel);
            DyeingTankModel dyeingTankModel=DyeingTankModel.instance(technology.getDyeingTank());
            technologyModel.setDyeingTankModel(dyeingTankModel);
            technologyModelList.add(technologyModel);
        }
        return technologyModelList;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(TechnologyModel model) {
        checkAddParams(model);//参数检验
        Technology technology = technologyDao.save(model.prototype());
        return Result.success().setData(technology);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(TechnologyModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        Technology technology = technologyDao.update(model.prototype());
        return Result.success().setData(technology);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(TechnologyModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Technology technology = technologyDao.find(model.getId());
        if (null == technology) {
            return Result.success("该对象不存在！");
        }
        TechnologyModel model_ = TechnologyModel.instance(technology);
        DyeingTankModel dyeManufacturerModel = DyeingTankModel.instance(technology.getDyeingTank());
        model_.setDyeingTankModel(dyeManufacturerModel);
        return Result.success().setData(model_);
    }

    /**
     * @description删除
     * @author: fangyong
     * @date 2018/11/15 16:51
     */
    @Override
    @Transactional
    public Result deleteById(TechnologyModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        Technology technology = technologyDao.update(model.prototype());
        if (null == technology) {
            Result.error("此记录不存在！");
        }
        technologyDao.delete(model.getId());
        return Result.success();
    }

    /**
     * @description 添加参数检验
     * @author: fangyong
     * @date 2018/11/15 16:52
     */
    public void checkAddParams(TechnologyModel model) {
        checkUpdateParams(model);
        model.setAddTime(DateUtil.getNow());

    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void checkUpdateParams(TechnologyModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空");
        }
//        if (model.getCloudId()<0) {
//            throw new BusinessException("云端id错误");
//        }
//        if (model.getStatus()<0) {
//            throw new BusinessException("云端状态错误");
//        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("描述不能为空");
//        }
//        if (StringUtil.isBlank(model.getAlias())) {
//            throw new BusinessException("材质别名不能为空");
//        }
//        if (StringUtil.isBlank(model.getName())) {
//            throw new BusinessException("材质名称不能为空");
//        }
        if (model.getFactoryId() > 0) {
            Factory factory = factoryDao.find(model.getFactoryId());
            if (null != factory) {
                model.setFactory(factory);
            }
        }
        if (model.getDyeingTankId() > 0) {
            DyeingTank dyeingTank = dyeingTankDao.find(model.getDyeingTankId());
            if (null != dyeingTank) {
                model.setDyeingTank(dyeingTank);
            }
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(TechnologyModel model) {
        model.setName(model.getName());
        model.setAddTime(DateUtil.getNow());
        model.setHeatingRate(model.getHeatingRate());
        model.setHeatingTime(model.getHeatingTime());
        model.setInsulationTime(model.getInsulationTime());
        model.setMainPumpSpeed(model.getMainPumpSpeed());
        model.setName(model.getName());
    }


}
