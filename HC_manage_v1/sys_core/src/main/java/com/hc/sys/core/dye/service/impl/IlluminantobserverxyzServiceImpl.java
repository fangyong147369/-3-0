package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.core.dye.dao.IlluminantobserverxyzDao;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import com.hc.sys.core.dye.service.IlluminantobserverxyzService;
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
public class IlluminantobserverxyzServiceImpl implements IlluminantobserverxyzService {
    @Resource
    private IlluminantobserverxyzDao illuminantobserverxyzDao;
    @Override
    @Transactional
    public List<Illuminantobserverxyz> findAll() {
        return illuminantobserverxyzDao.findAll();
    }

    @Override
    @Transactional
    public Result add(IlluminantobserverxyzModel model) {
        checkParams(model);
        Illuminantobserverxyz illuminantobserverxyz=illuminantobserverxyzDao.save(model.prototype());
        if(illuminantobserverxyz==null||illuminantobserverxyz.getId()<=0){
            return Result.error("对象添加失败");
        }
        return Result.success().setData(illuminantobserverxyz);
    }

    @Override
    @Transactional
    public Result update(IlluminantobserverxyzModel model)
    {
        updateParams(model);
        return null;
    }

    @Override
    @Transactional
    public Result getByWaveLengthAndAngle(IlluminantobserverxyzModel model) {
        Illuminantobserverxyz illuminantobserverxyz=null;
        if(model.getAngle()>0&&model.getId()>0){
            QueryParam queryParam=QueryParam.getInstance();
            queryParam.addParam("angle",model.getAngle());
            queryParam.addParam("id",model.getId());
            illuminantobserverxyz =illuminantobserverxyzDao.findByCriteriaForUnique(queryParam);
        }
        if (illuminantobserverxyz == null) {
            return Result.error("此对象不存在！");
        }
        return Result.success().setData(illuminantobserverxyz);
    }

    /**
     * 参数检验
     * @param model
     * @return
     */
    public void checkParams(IlluminantobserverxyzModel model){
        if(model.getId()<=0){
            Result.error("Id参数错误！");
        }
    }
    /**
     * 设置修改参数
     * @param model
     * @return
     */
    public void updateParams(IlluminantobserverxyzModel model){
        model.setId(model.getId());
        model.setAngle(model.getAngle());
        model.setX(model.getX());
        model.setY(model.getY());
        model.setZ(model.getZ());
    }
}
