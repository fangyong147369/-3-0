package com.hc.sys.core.dye.service.impl;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.ColorantDao;
import com.hc.sys.core.dye.dao.IlluminantDao;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.IlluminantModel;
import com.hc.sys.core.dye.service.ColorantService;
import com.hc.sys.core.dye.service.IlluminantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:23
 * @Version: 1.0.0.0
 */
@Service
public class ColorantServiceImpl implements ColorantService {
    @Resource
    private ColorantDao colorantDao;
    @Resource
    private IlluminantDao illuminantDao;
    @Resource
    private IlluminantService illuminantService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(ColorantModel model) {
        PageDataList<Colorant> pageDataList = colorantDao.list(model);
        PageDataList<ColorantModel> pageDataList_ = new PageDataList<ColorantModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<ColorantModel> list = new ArrayList<ColorantModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Colorant colorant : pageDataList.getList()) {
                ColorantModel model_ = ColorantModel.instance(colorant);
                IlluminantModel illuminantModel=new  IlluminantModel();
                illuminantModel.setAngle(colorant.getAngle());
                illuminantModel.setId(colorant.getIlluminantId());
                Result result= illuminantService.getByIdAndAngle(illuminantModel);
                 if(result.getCode()==1&&!StringUtil.isBlank(result.getData())){
                     Illuminant illuminant=(Illuminant)result.getData();
                     if(illuminant!=null){
                         model_.setIlluminantModel(IlluminantModel.instance(illuminant));
                     }
                 }
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
    public List<ColorantModel> findAll() {
        List<ColorantModel> modelList = new ArrayList<ColorantModel>();
        List<Colorant> colorantList = colorantDao.findAll();
        for (Colorant colorant : colorantList) {
            ColorantModel model_ = ColorantModel.instance(colorant);
            IlluminantModel illuminantModel=new  IlluminantModel();
            illuminantModel.setAngle(colorant.getAngle());
            illuminantModel.setId(colorant.getIlluminantId());
            Result result= illuminantService.getByIdAndAngle(illuminantModel);
            if(result.getCode()==1&&!StringUtil.isBlank(result.getData())){
                Illuminant illuminant=(Illuminant)result.getData();
                if(illuminant!=null){
                    model_.setIlluminantModel(IlluminantModel.instance(illuminant));
                }
            }
            modelList.add(model_);
        }
        return modelList;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(ColorantModel model) {
        checkAddParams(model);//检验参数
        Colorant colorant = colorantDao.save(model.prototype());
        return Result.success().setData(colorant);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(ColorantModel model) {
        Colorant colorant = colorantDao.find(model.getId());
        checkUpdateParams(model);//检验参数
        setUpdateParams(model);//设置修改参数
        colorantDao.update(colorant);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(ColorantModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Colorant colorant = colorantDao.find(model.getId());
        return Result.success().setData(colorant);
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    public void checkAddParams(ColorantModel model) {

        model.setAddTime(DateUtil.getNow());

    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    public void checkUpdateParams(ColorantModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
    }

    /**
     * 修改参数检验
     * @param model
     * @return
     */
    public void setUpdateParams(ColorantModel model) {
        Colorant colorant = colorantDao.find(model.getId());
        if (null == colorant) {
            throw new BusinessException("该对象不存在！");
        }
//        if(model.getIlluminantId()>0){
//            Illuminant illuminant=illuminantDao.find(model.getIlluminantId());
//            if(null!=illuminant){
//                model.setIlluminant(illuminant);
//            }
//        }
        model.setL(model.getL());
        model.setRgb(model.getRgb());
        model.setA(model.getA());
        model.setB(model.getB());
        model.setSpecularMode(model.getSpecularMode());
        model.setImagePath(model.getImagePath());
        model.setReflection400(model.getReflection400());
        model.setReflection410(model.getReflection410());
        model.setReflection420(model.getReflection420());
        model.setReflection430(model.getReflection430());
        model.setReflection440(model.getReflection440());
        model.setReflection450(model.getReflection450());
        model.setReflection460(model.getReflection460());
        model.setReflection470(model.getReflection470());
        model.setReflection480(model.getReflection480());
        model.setReflection490(model.getReflection490());
        model.setReflection500(model.getReflection500());
        model.setReflection510(model.getReflection510());
        model.setReflection520(model.getReflection520());
        model.setReflection530(model.getReflection530());
        model.setReflection540(model.getReflection540());
        model.setReflection550(model.getReflection550());
        model.setReflection560(model.getReflection560());
        model.setReflection570(model.getReflection570());
        model.setReflection580(model.getReflection580());
        model.setReflection590(model.getReflection590());
        model.setReflection600(model.getReflection600());
        model.setReflection610(model.getReflection610());
        model.setReflection620(model.getReflection620());
        model.setReflection630(model.getReflection630());
        model.setReflection640(model.getReflection640());
        model.setReflection650(model.getReflection650());
        model.setReflection660(model.getReflection660());
        model.setReflection670(model.getReflection670());
        model.setReflection680(model.getReflection680());
        model.setReflection690(model.getReflection690());
        model.setReflection700(model.getReflection700());
    }

}
