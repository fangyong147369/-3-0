package com.hc.sys.core.material.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.FactoryDao;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.model.FactoryModel;
import com.hc.sys.core.material.service.FactoryService;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 13:58
 * @Version: 1.0.0.0
 */
public class FactoryServiceImpl implements FactoryService {
    @Resource
    private FactoryDao factoryDao;

    @Override
    @Transactional
    public Object list(FactoryModel model) {
        PageDataList<Factory> pageDataList = factoryDao.list(model);
        PageDataList<FactoryModel> pageDataList_ = new PageDataList<FactoryModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<FactoryModel> list = new ArrayList<FactoryModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Factory factory : pageDataList.getList()) {
                FactoryModel model_ = FactoryModel.instance(factory);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    @Override
    @Transactional
    public List<Factory> findAll() {
        return factoryDao.findAll();
    }

    @Override
    @Transactional
    public Result add(FactoryModel model) {
        checkAddParams(model);
        Factory factory=factoryDao.save(model.prototype());
        return Result.success().setData(factory);
    }

    @Override
    @Transactional
    public Result update(FactoryModel model) {
        checkAddParams(model);
        setUpdateParams(model);
        Factory factory=factoryDao.update(model.prototype());
        return Result.success().setData(factory);
    }

    @Override
    @Transactional
    public Result getById(FactoryModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        Factory factory = factoryDao.find(model.getId());
        if (null == factory) {
            return Result.success("该对象不存在！");
        }
        return Result.success().setData(factory);
    }


    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(FactoryModel model) {
        checkParams(model);
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkParams(FactoryModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("染厂名称不能为空");
        }
        if (StringUtil.isBlank(model.getWorkshop())) {
            throw new BusinessException("染厂车间不能为空");
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void setUpdateParams(FactoryModel model) {
        model.setName(model.getName());
        model.setWorkshop(model.getWorkshop());
    }

}
