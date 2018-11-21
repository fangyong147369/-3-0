package com.hc.sys.core.dye.service.impl;

import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.core.dye.dao.IlluminantrelativeenergydistributionDao;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.entity.Illuminantrelativeenergydistribution;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import com.hc.sys.core.dye.model.IlluminantrelativeenergydistributionModel;
import com.hc.sys.core.dye.service.IlluminantrelativeenergydistributionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:31
 * @Version: 1.0.0.0
 */
@Service
public class IlluminantrelativeenergydistributionServiceImpl implements IlluminantrelativeenergydistributionService {
    @Resource
    private IlluminantrelativeenergydistributionDao illuminantrelativeenergydistributionDao;

    @Override
    @Transactional
    public List<Illuminantrelativeenergydistribution> findAll() {
        return illuminantrelativeenergydistributionDao.findAll();
    }

    @Override
    @Transactional
    public Result add(IlluminantrelativeenergydistributionModel model) {
        checkParams(model);
        Illuminantrelativeenergydistribution illuminantrelativeenergydistribution =illuminantrelativeenergydistributionDao.save(model.prototype());
        return Result.success().setData(illuminantrelativeenergydistribution);
    }

    @Override
    @Transactional
    public Result update(IlluminantrelativeenergydistributionModel model) {
        updateParams(model);
        Illuminantrelativeenergydistribution illuminantrelativeenergydistribution =illuminantrelativeenergydistributionDao.update(model.prototype());
        return Result.success().setData(illuminantrelativeenergydistribution);
    }

    @Override
    @Transactional
    public Result getByIdAndWaveLength(IlluminantrelativeenergydistributionModel model) {
        Illuminantrelativeenergydistribution illuminantrelativeenergydistribution = null;
        if (model.getId() > 0 && model.getWaveLength() > 0) {
            QueryParam queryParam = QueryParam.getInstance();
            queryParam.addParam("id", model.getId());
            queryParam.addParam("waveLength", model.getWaveLength());
            illuminantrelativeenergydistribution = illuminantrelativeenergydistributionDao.findByCriteriaForUnique(queryParam);

        }
        if(illuminantrelativeenergydistribution==null){
            return Result.error("此对象不存在！");
        }
        return Result.success().setData(illuminantrelativeenergydistribution);
    }


    /**
     * 参数检验
     * @param model
     * @return
     */
    public void checkParams(IlluminantrelativeenergydistributionModel model){
        if(model.getWaveLength()<=0){
            Result.error("wave_length参数错误！");
        }
        if(model.getId()<=0){
            Result.error("Id参数错误！");
        }
    }

    /**
     * 参数检验
     * @param model
     * @return
     */
    public void updateParams(IlluminantrelativeenergydistributionModel model){
        model.setWaveLength(model.getWaveLength());
        model.setId(model.getId());
        model.setValue(model.getValue());
    }
}
