package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.dao.ColorantDao;
import com.hc.sys.core.dye.dao.DyeDao;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.material.dao.MaterialDao;
import com.hc.sys.core.material.dao.MaterialProofingDao;
import com.hc.sys.core.material.entity.Material;
import com.hc.sys.core.material.entity.MaterialProofing;
import com.hc.sys.core.material.model.MaterialModel;
import com.hc.sys.core.material.model.MaterialProofingModel;
import com.hc.sys.core.material.service.MaterialProofingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/11/5 11:10
 * @Version: 1.0.0.0
 */
@Service
public class MaterialProofingServiceImpl implements MaterialProofingService {
    @Resource
    private MaterialProofingDao materialProofingDao;
    @Resource
    public MaterialDao materialDao;
    @Resource
    public DyeDao dyeDao;
    @Resource
    public ColorantDao colorantDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public Result list( MaterialProofingModel model) {
        PageDataList<MaterialProofing> pageDataList = materialProofingDao.list(model);
        PageDataList<MaterialProofingModel> pageDataList_ = new PageDataList< MaterialProofingModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<MaterialProofingModel> list = new ArrayList< MaterialProofingModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (MaterialProofing materialProofing : pageDataList.getList()) {
                MaterialProofingModel model_ = MaterialProofingModel.instance(materialProofing);
                MaterialModel materialModel = MaterialModel.instance(materialProofing.getMaterial());
                model_.setMaterialModel(materialModel);
                DyeModel dyeModel = DyeModel.instance(materialProofing.getDye());
                model_.setDyeModel(dyeModel);
                ColorantModel colorantModel=ColorantModel.instance(materialProofing.getColorant());
                model_.setColorantModel(colorantModel);
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
    public List<MaterialProofingModel> findList() {
        List<MaterialProofingModel> list = new ArrayList< MaterialProofingModel>();
        List<MaterialProofing> modelList = materialProofingDao.findList();
        if (modelList!= null &&modelList.size() > 0) {
            for (MaterialProofing materialProofing :modelList ) {
                MaterialProofingModel model_ = MaterialProofingModel.instance(materialProofing);
                if (materialProofing.getMaterial() != null) {
                    MaterialModel materialModel = MaterialModel.instance(materialProofing.getMaterial());
                    model_.setMaterialModel(materialModel);
                }
                if (materialProofing.getDye() != null) {
                    DyeModel dyeModel = DyeModel.instance(materialProofing.getDye());
                    model_.setDyeModel(dyeModel);
                }
                if (materialProofing.getColorant() != null) {
                    ColorantModel colorantModel = ColorantModel.instance(materialProofing.getColorant());
                    model_.setColorantModel(colorantModel);
                }
                list.add(model_);
            }
        }
        return list;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(MaterialProofingModel model) {
        checkAddParams(model);//参数检验
        MaterialProofing materialProofing=materialProofingDao.save(model.prototype());
        return Result.success().setData(materialProofing);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(MaterialProofingModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        MaterialProofing materialProofing=materialProofingDao.update(model.prototype());
        return Result.success().setData(materialProofing);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(MaterialProofingModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        MaterialProofing materialProofing=materialProofingDao.find(model.getId());
        if (null == materialProofing) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(materialProofing);
    }

    @Override
    public Result deleteById(MaterialProofingModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        MaterialProofing materialProofing=materialProofingDao.find(model.getId());
        if (null == materialProofing) {
            Result.error("ID参数错误！");
        }
        materialProofingDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(MaterialProofingModel model) {
        checkUpdateParams(model);
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(MaterialProofingModel model) {
//        if (model.getStatus()<0) {
//            throw new BusinessException("云端状态错误");
//        }
        if(model.getDyeId()>0){
            Dye dye=dyeDao.find(model.getDyeId());
            if(null!=dye){
                model.setDye(dye);
            }
        }
        if(model.getColorantId()>0){
            Colorant colorant=colorantDao.find(model.getColorantId());
            if(null!=colorant){
                model.setColorant(colorant);
            }
        }
        if(model.getMaterialId()>0){
            Material material=materialDao.find(model.getMaterialId());
            if(null!=material){
                model.setMaterial(material);
            }
        }
    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(MaterialProofingModel model) {
        if (model.getId() <= 0) {
            Result.error("参数错误");
        }
        MaterialProofing materialProofing= materialProofingDao.find(model.getId());
        if (null == materialProofing) {
            Result.error("该对象不存在！");
        }
        model.setStatus(model.getStatus());
        model.setConc(model.getConc());

    }


}
