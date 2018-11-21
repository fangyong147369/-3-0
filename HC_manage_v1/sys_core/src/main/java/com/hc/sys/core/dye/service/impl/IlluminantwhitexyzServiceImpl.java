package com.hc.sys.core.dye.service.impl;

import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.core.dye.dao.IlluminantwhitexyzDao;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import com.hc.sys.core.dye.model.IlluminantwhitexyzModel;
import com.hc.sys.core.dye.service.IlluminantwhitexyzService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:29
 * @Version: 1.0.0.0
 */
@Service
public class IlluminantwhitexyzServiceImpl implements IlluminantwhitexyzService {
    @Resource
    private IlluminantwhitexyzDao illuminantwhitexyzDao;

    @Override
    @Transactional
    public List<Illuminantwhitexyz> findAll() {
        return illuminantwhitexyzDao.findAll();
    }

    @Override
    @Transactional
    public Result add(IlluminantwhitexyzModel model) {
        checkParams(model);
        Illuminantwhitexyz illuminantwhitexyz = illuminantwhitexyzDao.save(model.prototype());
        if (illuminantwhitexyz == null || illuminantwhitexyz.getId() <= 0) {
            return Result.error("对象添加失败");
        }
        return Result.success().setData(illuminantwhitexyz);
    }

    @Override
    @Transactional
    public Result update(IlluminantwhitexyzModel model) {
        getByIdAndAngle(model);
        updateParams(model);
        Illuminantwhitexyz illuminantwhitexyz = illuminantwhitexyzDao.update(model.prototype());
        return Result.success().setData(illuminantwhitexyz);
    }

    @Override
    @Transactional
    public Result getByIdAndAngle(IlluminantwhitexyzModel model) {
        Illuminantwhitexyz illuminantwhitexyz = null;
        if (model.getAngle() > 0 && model.getId() > 0) {
            QueryParam queryParam = QueryParam.getInstance();
            queryParam.addParam("angle", model.getAngle());
            queryParam.addParam("id", model.getId());
            illuminantwhitexyz = illuminantwhitexyzDao.findByCriteriaForUnique(queryParam);
        }
        if (illuminantwhitexyz == null) {
            return Result.error("此对象不存在！");
        }
        return Result.success().setData(illuminantwhitexyz);
    }

    /**
     * 参数检验
     *
     * @param model
     * @return
     */
    public void checkParams(IlluminantwhitexyzModel model) {
        if (model.getId() <= 0) {
            Result.error("Id参数错误！");
        }
    }

    /**
     * 设置检验
     *
     * @param model
     * @return
     */
    public void updateParams(IlluminantwhitexyzModel model) {
        model.setId(model.getId());
        model.setAngle(model.getAngle());
        model.setX(model.getX());
        model.setY(model.getY());
        model.setZ(model.getZ());
    }
}
