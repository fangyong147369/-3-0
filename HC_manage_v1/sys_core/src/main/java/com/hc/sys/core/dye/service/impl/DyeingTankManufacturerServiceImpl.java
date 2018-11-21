package com.hc.sys.core.dye.service.impl;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeingTankManufacturerDao;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import com.hc.sys.core.dye.service.DyeingTankManufacturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:20
 * @Version: 1.0.0.0
 */
@Service
public class DyeingTankManufacturerServiceImpl implements DyeingTankManufacturerService {
    @Resource
    private DyeingTankManufacturerDao dyeingTankManufacturerDao;

    /**
     * @description 分页列表
     * @author: fangyong
     * @date 2018/11/1 11:24
     */
    @Override
    @Transactional
    public Result list(DyeingTankManufacturerModel model) {
        PageDataList<DyeingTankManufacturer> pageDataList = dyeingTankManufacturerDao.list(model);
        PageDataList<DyeingTankManufacturerModel> pageDataList_ = new PageDataList<DyeingTankManufacturerModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeingTankManufacturerModel> list = new ArrayList<DyeingTankManufacturerModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (DyeingTankManufacturer dyeingTankManufacturer : pageDataList.getList()) {
                DyeingTankManufacturerModel model_ = DyeingTankManufacturerModel.instance(dyeingTankManufacturer);
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
    public List<DyeingTankManufacturer> findAll() {
        List<DyeingTankManufacturer> list = new ArrayList();
        List<DyeingTankManufacturer> dyeingTankManufacturerList = dyeingTankManufacturerDao.findAll();
        for (DyeingTankManufacturer dyeingTankManufacturer : dyeingTankManufacturerList) {
            list.add(dyeingTankManufacturer);
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
    public Result add(DyeingTankManufacturerModel model) {
        checkAddParams(model);//参数检验
        DyeingTankManufacturer dyeingTankManufacturer = dyeingTankManufacturerDao.save(model.prototype());
        return Result.success().setData(dyeingTankManufacturer);
    }

    /**
     * @description update
     * @author: fangyong
     * @date 2018/11/1 11:25
     */
    @Override
    @Transactional
    public Result update(DyeingTankManufacturerModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        DyeingTankManufacturer dyeingTankManufacturer = dyeingTankManufacturerDao.update(model.prototype());
        return Result.success().setData(dyeingTankManufacturer);
    }

    /**
     * @description getById
     * @author: fangyong
     * @date 2018/11/1 11:26
     */
    @Override
    public Result getById(DyeingTankManufacturerModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        DyeingTankManufacturer dyeingTankManufacturer = dyeingTankManufacturerDao.find(model.getId());
        if (null == dyeingTankManufacturer) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeingTankManufacturer);
    }

    @Override
    public Result deleteById(DyeingTankManufacturerModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        DyeingTankManufacturer dyeingTankManufacturer = dyeingTankManufacturerDao.find(model.getId());
        if (null == dyeingTankManufacturer) {
            Result.error("ID参数错误！");
        }
        dyeingTankManufacturerDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(DyeingTankManufacturerModel model) {
        model.setAddTime(DateUtil.getNow());
        checkUpdateParams(model);

    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(DyeingTankManufacturerModel model) {
        if (StringUtil.isBlank(model.getCompany())) {
            throw new BusinessException("公司名称不能为空");
        }
//        if (StringUtil.isBlank(model.getZipcode())) {
//            throw new BusinessException("邮编不能为空");
//        }
//        if (StringUtil.isBlank(model.getAddress())) {
//            throw new BusinessException("公司地址不能为空");
//        }
//        if (StringUtil.isBlank(model.getCountry())) {
//            throw new BusinessException("所属国家不能为空");
//        }
//        if (StringUtil.isBlank(model.getState())) {
//            throw new BusinessException("所属州/省不能为空");
//        }
//        if (StringUtil.isBlank(model.getCity())) {
//            throw new BusinessException("所属城市不能为空");
//        }
//        if (StringUtil.isBlank(model.getPhoneNumber())) {
//            throw new BusinessException("联系电话不能为空");
//        }

    }

    /**
     * @description 修改参数检验
     * @author: fangyong
     * @date 2018/11/16 17:38
     */
    public void setUpdateParams(DyeingTankManufacturerModel model) {
        model.setCompany(model.getCompany());
        model.setZipcode(model.getZipcode());
        model.setAddress(model.getAddress());
        model.setCountry(model.getCountry());
        model.setState(model.getState());
        model.setCity(model.getCity());
        model.setPhoneNumber(model.getPhoneNumber());
        model.setEmail(model.getEmail());
        model.setFaxNumber(model.getFaxNumber());
    }
}
