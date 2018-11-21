package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.*;
import com.hc.sys.core.dye.entity.*;
import com.hc.sys.core.dye.model.*;
import com.hc.sys.core.dye.service.DyeService;
import com.hc.sys.core.manage.dao.OperatorDao;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:13
 * @Version: 1.0.0.0
 */
@Service
public class DyeServiceImpl implements DyeService {
    @Resource
    public DyeDao dyeDao;
    @Resource
    public OperatorDao operatorDao;
    @Resource
    public DyeTypeDao dyeTypeDao;
    @Resource
    public DyeManufacturerDao dyeManufacturerDao;
    @Resource
    public DyeColorDao dyeColorDao;


    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public Result list(DyeModel model) {
        PageDataList<Dye> pageDataList = dyeDao.list(model);
        PageDataList<DyeModel> pageDataList_ = new PageDataList<DyeModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeModel> list = new ArrayList<DyeModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Dye dye : pageDataList.getList()) {
                DyeModel model_ = DyeModel.instance(dye);
                OperatorModel operatorModel=OperatorModel.instance(dye.getOperator());
                model_.setOperatorModel(operatorModel);
                DyeColorModel dyeColorModel= DyeColorModel.instance(dye.getDyeColor());
                model_.setDyeColorModel(dyeColorModel);
                DyeTypeModel dyeTypeModel=DyeTypeModel.instance(dye.getDyeType());
                model_.setDyeTypeModel(dyeTypeModel);
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
    public  List<DyeModel> findAll() {
        List<DyeModel> list=new ArrayList<DyeModel>();
        List<Dye> getList=dyeDao.findAll();
        for (Dye dye:getList) {
            DyeModel dyeModel=DyeModel.instance(dye);
            if(dye.getDyeColor()!=null)
                dyeModel.setDyeColorModel(DyeColorModel.instance(dye.getDyeColor()));
            if(dye.getDyeType()!=null)
            dyeModel.setDyeTypeModel(DyeTypeModel.instance(dye.getDyeType()));
            if(dye.getDyeManufacturer()!=null)
            dyeModel.setDyeManufacturerModel(DyeManufacturerModel.instance(dye.getDyeManufacturer()));
            list.add(dyeModel);
        }
        return list;
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(DyeModel model) {
        checkAddParams(model);//参数检验
        Dye  dye=dyeDao.save(model.prototype());
        return Result.success().setData(dye);
    }
    /**
     * test--------批量添加
     * @param dyes
     * @return
     */
    @Override
    @Transactional
    public Result addList(List<Dye> dyes) {
        List<Dye> dyeArrayList=new ArrayList<Dye>();
        for (Dye dye:dyes) {
            dye.setAddTime(DateUtil.getNow());
            dyeArrayList.add(dye);
        }
        dyeDao.save(dyeArrayList);
        return Result.success().setData(dyeArrayList);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(DyeModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        Dye dye=null;
        try {
            dye=dyeDao.update(model.prototype());
            if (null == dye) {
                Result.error("该对象更新失败！");
            }
        }catch (Exception e){
            Result.error(e.getMessage());
        }
        return Result.success().setData(dye);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(DyeModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Dye dye = dyeDao.find(model.getId());
        if (null == dye) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dye);
    }

    @Override
    public Result deleteById(DyeModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        Dye dye = dyeDao.find(model.getId());
        if (null == dye) {
            Result.error("ID参数错误！");
        }
        dyeDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(DyeModel model) {
        checkUpdateParams(model);
        model.setAddTime(DateUtil.getNow());

    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(DyeModel model) {
//        if (model.getCloudId()<0) {
//            throw new BusinessException("云端id错误");
//        }
//        if (model.getCloudId()<0) {
//            throw new BusinessException("云端id错误");
//        }
//        if (model.getStatus()<0) {
//            throw new BusinessException("云端状态错误");
//        }
//        if (model.getStrength()<0) {
//            throw new BusinessException("染料力份参数错误");
//        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("描述不能为空");
//        }
        if(model.getOperatoId()>0){
            Operator operator=operatorDao.find(model.getOperatoId());
            if(null!=operator){
                model.setOperator(operator);
            }
        }
        if(model.getDyeTypeId()>0){
            DyeType dyeType=dyeTypeDao.find(model.getDyeTypeId());
            if(null!=dyeType){
                model.setDyeType(dyeType);
            }
        }
        if(model.getDyeColorId()>0){
            DyeColor dyeColor=dyeColorDao.find(model.getDyeColorId());
            if(null!=dyeColor){
                model.setDyeColor(dyeColor);
            }
        }
        if(model.getDyeManufacturerId()>0){
            DyeManufacturer dyeManufacturer=dyeManufacturerDao.find(model.getDyeManufacturerId());
            if(null!=dyeManufacturer){
                model.setDyeManufacturer(dyeManufacturer);
            }
        }

    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(DyeModel model) {
        if (model.getId() <= 0) {
           Result.error("参数错误");
        }
        Dye dye = dyeDao.find(model.getId());
        if (null == dye) {
            Result.error("该对象不存在！");
        }
        model.setOperator(dye.getOperator());
        model.setAddTime(dye.getAddTime());
        model.setName(model.getName());
        model.setAlias(model.getAlias());
        model.setStrength(model.getStrength());
        model.setPrice(model.getPrice());
        model.setExterior(model.getExterior());
        model.setDescription(model.getDescription());
    }


}
