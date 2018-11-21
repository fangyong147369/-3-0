package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeManufacturerDao;
import com.hc.sys.core.dye.entity.DyeManufacturer;
import org.springframework.transaction.annotation.Transactional;
import com.hc.sys.core.dye.model.DyeManufacturerModel;
import com.hc.sys.core.dye.service.DyeManufacturerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 染料厂商
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:13
 * @Version: 1.0.0.0
 */
@Service
public class DyeManufacturerServiceImpl implements DyeManufacturerService {
    @Resource
    private DyeManufacturerDao dyeManufacturerDao;
    /**
     * 列表
     * @param model
     * @return
     */
    @Override
    public Result list(DyeManufacturerModel model) {
        PageDataList<DyeManufacturer> pageDataList = dyeManufacturerDao.list(model);
        PageDataList<DyeManufacturerModel> pageDataList_ = new PageDataList<DyeManufacturerModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeManufacturerModel> list = new ArrayList<DyeManufacturerModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (DyeManufacturer dyeManufacturer : pageDataList.getList()) {
                DyeManufacturerModel model_ = DyeManufacturerModel.instance(dyeManufacturer);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }
    /**
     * 无分页列表
     * @param
     * @return
     */
    @Override
    public List<DyeManufacturer> findAll() {
        List<DyeManufacturer> list=new ArrayList<DyeManufacturer>();
        List<DyeManufacturer> manufacturerDaoList=dyeManufacturerDao.findAll();
        for (DyeManufacturer dyeManufacturer:manufacturerDaoList) {
            list.add(dyeManufacturer);
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
    public Result add(DyeManufacturerModel model) {
        checkAddParams(model);//参数检验
        DyeManufacturer  dyeManufacturer=dyeManufacturerDao.save(model.prototype());
        return Result.success().setData(dyeManufacturer);
    }
    /**
     * 修改
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(DyeManufacturerModel model) {
        checkUpdateParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        dyeManufacturerDao.update(model.prototype());
        return Result.success();
    }
    /**
     * 获取
     * @param model
     * @return
     */
    @Override
    public Result getById(DyeManufacturerModel model) {
        if(model.getId()<=0){
            return Result.error("参数错误");
        }
        DyeManufacturer dyeManufacturer=dyeManufacturerDao.find(model.getId());
        if(null==dyeManufacturer){
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeManufacturer);
    }

    @Override
    public Result deleteById(DyeManufacturerModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        DyeManufacturer dyeManufacturer = dyeManufacturerDao.find(model.getId());
        if (null == dyeManufacturer) {
            Result.error("ID参数错误！");
        }
        dyeManufacturerDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     * @param model
     * @return
     */
    public void checkAddParams(DyeManufacturerModel model){
        checkUpdateParams(model);
        model.setAddTime(DateUtil.getNow());

    }
    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void checkUpdateParams(DyeManufacturerModel model){
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
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(DyeManufacturerModel model){
        if(model.getId()<=0){
             Result.error("参数错误");
        }
        DyeManufacturer dyeManufacturer=dyeManufacturerDao.find(model.getId());
        if(null==dyeManufacturer){
             Result.success("该对象不存在！");
        }
        model.setAddTime(dyeManufacturer.getAddTime());
        model.setCompany(model.getCompany());
        model.setZipcode(model.getZipcode());
        model.setAddress(model.getAddress());
        model.setCountry(model.getCountry());
        model.setState(model.getState());
        model.setCity(model.getCity());
        model.setPhoneNumber(model.getPhoneNumber());
        model.setEmail(model.getEmail());
        model.setFaxNumber( model.getFaxNumber());
    }
}
