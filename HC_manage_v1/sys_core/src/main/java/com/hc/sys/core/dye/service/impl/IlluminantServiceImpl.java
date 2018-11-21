package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.entity.AngleIdPkEntity;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.IlluminantDao;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.model.IlluminantModel;
import com.hc.sys.core.dye.service.IlluminantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 光源
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:29
 * @Version: 1.0.0.0
 */
@Service
public class IlluminantServiceImpl implements IlluminantService {
    @Resource
    private IlluminantDao illuminantDao;

    @Override
    @Transactional
    public List<Illuminant> findAll()
    {
        return illuminantDao.findAll();
    }

    /**
     * 添加
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(IlluminantModel model) {
        checkParams(model);//添加参数检验
        Illuminant illuminantEntiy=illuminantDao.save(model.prototype());
        return Result.success().setData(illuminantEntiy);
    }
    /**
     * 修改
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(IlluminantModel model) {
        checkParams(model);
        setUpdateParams(model);//设置参数
        Illuminant illuminantEntiy= illuminantDao.update(model.prototype());
        return Result.success().setData(illuminantEntiy);
    }
    /**
     * 查找
     * @param model
     * @return
     */
    @Override
    @Transactional
    public  Result getByIdAndAngle(IlluminantModel model) {
        Illuminant illuminant =null;
        if(model.getAngle()>0&&model.getId()>0){
            QueryParam queryParam=QueryParam.getInstance();
            queryParam.addParam("angle",model.getAngle());
            queryParam.addParam("id",model.getId());
            illuminant =illuminantDao.findByCriteriaForUnique(queryParam);
        }
        if(illuminant==null){
            return Result.error("此对象不存在！");
        }
        return Result.success().setData(illuminant);
    }
    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void checkParams(IlluminantModel model){
        if(model.getId()>0){
            model.setId(model.getId());
        }
        if(model.getAngle()>0){
        model.setAngle(model.getAngle());
        }
//        if (StringUtil.isBlank(model.getName())) {
//            throw new BusinessException("光源名称不能为空.");
//        }
//        if (model.getAngle()<=0) {
//            throw new BusinessException("光源观察角度非法.");
//        }
//        if (StringUtil.isBlank(model.getDescription())) {
//            throw new BusinessException("光源描述不能为空");
//        }
    }
    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(IlluminantModel model){
        Illuminant illuminant=null;
        if(model.getAngle()>0&&model.getId()>0){
            QueryParam queryParam=QueryParam.getInstance();
            queryParam.addParam("angle",model.getAngle());
            queryParam.addParam("id",model.getId());
            illuminant =illuminantDao.findByCriteriaForUnique(queryParam);
            if (null==illuminant) {
               Result.error("该条光源记录已被删除或停用！");
            }
        }
        model.setName(model.getName());
        model.setAngle(model.getAngle());
        model.setDescription(model.getDescription());
    }
}
