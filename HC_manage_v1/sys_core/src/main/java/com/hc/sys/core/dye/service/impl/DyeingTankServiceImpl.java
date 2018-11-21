package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeingTankDao;
import com.hc.sys.core.dye.dao.DyeingTankManufacturerDao;
import com.hc.sys.core.dye.entity.DyeingTank;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import com.hc.sys.core.dye.model.DyeingTankModel;
import com.hc.sys.core.dye.service.DyeingTankManufacturerService;
import com.hc.sys.core.dye.service.DyeingTankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染缸
 * @Author: fangyong
 * @CreateDate: 2018/11/1 12:35
 * @Version: 1.0.0.0
 */
@Service
public class DyeingTankServiceImpl implements DyeingTankService {
    @Resource
    private DyeingTankDao dyeingTankDao;
    @Resource
    private DyeingTankManufacturerDao dyeingTankManufacturerDao;
  /**
   * @description 分页列表
   * @author: fangyong
   * @date 2018/11/1 11:24
   */
    @Override
    @Transactional
    public Result list(DyeingTankModel model) {
        PageDataList<DyeingTank> pageDataList = dyeingTankDao.list(model);
        PageDataList<DyeingTankModel> pageDataList_ = new PageDataList<DyeingTankModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeingTankModel> list = new ArrayList<DyeingTankModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for ( DyeingTank dyeingTank : pageDataList.getList()) {
                DyeingTankModel model_ = DyeingTankModel.instance(dyeingTank);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }
/**
 * @description 无分页列表
 * @author: fangyong
 * @date 2018/11/1 11:24
 */
    @Override
    @Transactional
    public List<DyeingTank> findAll() {
        List<DyeingTank> list=new ArrayList();
        List<DyeingTank> dyeingTankList=dyeingTankDao.findAll();
        for (DyeingTank dyeingTank:dyeingTankList) {
            list.add(dyeingTank);
        }
        return list;
    }
   /**
    * @description add
    * @author: fangyong
    * @date 2018/11/1 11:25
    */
    @Override
    @Transactional
    public Result add(DyeingTankModel model) {
        checkAddParams(model);//参数检验
        DyeingTank  dyeingTank=dyeingTankDao.save(model.prototype());
        return Result.success().setData(dyeingTank);
    }
    /**
     * @description update
     * @author: fangyong
     * @date 2018/11/1 11:25
     */
    @Override
    @Transactional
    public Result update(DyeingTankModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        DyeingTank dyeingTank= dyeingTankDao.update(model.prototype());
        return Result.success().setData(dyeingTank);
    }
    /**
     * @description getById
     * @author: fangyong
     * @date 2018/11/1 11:26
     */
    @Override
    @Transactional
    public Result getById(DyeingTankModel model) {
        if(model.getId()<=0){
            return Result.error("参数错误");
        }
        DyeingTank dyeingTank=dyeingTankDao.find(model.getId());
        if(null==dyeingTank){
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeingTank);
    }

    @Override
    public Result deleteById(DyeingTankModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        DyeingTank dyeingTank=dyeingTankDao.find(model.getId());
        if (null == dyeingTank) {
            Result.error("ID参数错误！");
        }
        dyeingTankDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     * @param model
     * @return
     */
    public void checkAddParams(DyeingTankModel model){
        checkUpdateParams(model);
        if(model.getDyeingTankManufacturerId()>0){
            DyeingTankManufacturer dyeingTankManufacturer=dyeingTankManufacturerDao.find(model.getDyeingTankManufacturerId());
            if(null!=dyeingTankManufacturer){
                model.setDyeingTankManufacturer(dyeingTankManufacturer);
            }
        }
    }
    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void checkUpdateParams(DyeingTankModel model){
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空");
        }
        if (StringUtil.isBlank(model.getModel())) {
            throw new BusinessException("型号不能为空");
        }

    }
    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(DyeingTankModel model){
        model.setName(model.getName());
        model.setModel(model.getModel());
        if(model.getDyeingTankManufacturerId()>0){
            DyeingTankManufacturer dyeingTankManufacturer=dyeingTankManufacturerDao.find(model.getDyeingTankManufacturerId());
            if(null!=dyeingTankManufacturer){
                model.setDyeingTankManufacturer(dyeingTankManufacturer);
            }
        }

    }
}
