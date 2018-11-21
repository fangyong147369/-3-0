package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeDao;
import com.hc.sys.core.dye.dao.DyeGroupDao;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.service.DyeGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 16:25
 * @Version: 1.0.0.0
 */
@Service
public class DyeGroupServiceImpl implements DyeGroupService {
    @Resource
    public DyeGroupDao dyeGroupDao;
    @Resource
    public DyeDao dyeDao;

    /**
     * 分页列表
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public Result list(DyeGroupModel model) {
        PageDataList<DyeGroup> pageDataList = dyeGroupDao.list(model);
        PageDataList<DyeGroupModel> pageDataList_ = new PageDataList<DyeGroupModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DyeGroupModel> list = new ArrayList<DyeGroupModel>();
        List<DyeModel> modelList = new ArrayList<DyeModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (DyeGroup dyeGroup : pageDataList.getList()) {
                DyeGroupModel model_ = DyeGroupModel.instance(dyeGroup);
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
    @javax.transaction.Transactional
    public List<DyeGroup> findAll() {

        return dyeGroupDao.findAll();
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(DyeGroupModel model) {
        checkAddParams(model);//参数检验
        model.setAddTime(DateUtil.getNow());
        DyeGroup dyeGroup = dyeGroupDao.save(model.prototype());
        return Result.success().setData(dyeGroup);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(DyeGroupModel model) {
        checkAddParams(model);//参数检验
        setUpdateParams(model);//设置修改参数
        DyeGroup dyeGroup = dyeGroupDao.update(model.prototype());
        return Result.success().setData(dyeGroup);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(DyeGroupModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        DyeGroup dyeGroup = dyeGroupDao.find(model.getId());
        if (null == dyeGroup) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(dyeGroup);
    }

    @Override
    public Result deleteById(DyeGroupModel model) {
        if (model.getId() <= 0) {
            Result.error("ID参数错误");
        }
        DyeGroup dyeGroup = dyeGroupDao.find(model.getId());
        if (null == dyeGroup) {
            Result.error("ID参数错误！");
        }
        dyeGroupDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(DyeGroupModel model) {
        checkUpdateParams(model);
        String[] id = model.getDyeIds().split(",");
        if (id.length == 1) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(0);
            model.setDyeid3(0);
            model.setDyeid4(0);
            model.setDyeid5(0);
            model.setDyeid6(0);
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 2) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(0);
            model.setDyeid4(0);
            model.setDyeid5(0);
            model.setDyeid6(0);
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 3) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(0);
            model.setDyeid5(0);
            model.setDyeid6(0);
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 4) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(0);
            model.setDyeid6(0);
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 5) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(0);
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 6) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(Long.parseLong(id[5]));
            model.setDyeid7(0);
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 7) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(Long.parseLong(id[5]));
            model.setDyeid7(Long.parseLong(id[6]));
            model.setDyeid8(0);
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 8) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(Long.parseLong(id[5]));
            model.setDyeid7(Long.parseLong(id[6]));
            model.setDyeid8(Long.parseLong(id[7]));
            model.setDyeid9(0);
            model.setDyeid10(0);
        }
        if (id.length == 9) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(Long.parseLong(id[5]));
            model.setDyeid7(Long.parseLong(id[6]));
            model.setDyeid8(Long.parseLong(id[7]));
            model.setDyeid9(Long.parseLong(id[8]));
            model.setDyeid10(0);
        }
        if (id.length == 10) {
            model.setDyeid1(Long.parseLong(id[0]));
            model.setDyeid2(Long.parseLong(id[1]));
            model.setDyeid3(Long.parseLong(id[2]));
            model.setDyeid4(Long.parseLong(id[3]));
            model.setDyeid5(Long.parseLong(id[4]));
            model.setDyeid6(Long.parseLong(id[5]));
            model.setDyeid7(Long.parseLong(id[6]));
            model.setDyeid7(Long.parseLong(id[7]));
            model.setDyeid9(Long.parseLong(id[8]));
            model.setDyeid10(Long.parseLong(id[9]));
        }

    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(DyeGroupModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("染料组名称不能为空");
        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("染料组描述不能为空");
//        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(DyeGroupModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        DyeGroup dyeGroup = dyeGroupDao.find(model.getId());
        if (null == dyeGroup) {
            throw new BusinessException("该对象不存在！");
        }
        model.setAddTime(dyeGroup.getAddTime());
        model.setName(model.getName());
        model.setDescription(model.getDescription());
    }


}
