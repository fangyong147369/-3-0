package com.hc.sys.api.config;//package com.hc.sys.api.config;

import com.hc.sys.common.cache.RedisCacheUtil;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.core.common.idService.CacheID;
import com.hc.sys.core.dye.entity.*;
import com.hc.sys.core.dye.service.*;
import com.hc.sys.core.material.entity.Concentration;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.model.ConcentrationModel;
import com.hc.sys.core.material.model.FormulaModel;
import com.hc.sys.core.material.service.ConcentrationService;
import com.hc.sys.core.material.service.FormulaService;
import com.hc.sys.netty.ccalculationcolor.DBUtilTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化数据
 */
@Component
public class Init implements CommandLineRunner {
    @Resource
    private FormulaService formulaService;
    @Override
    public void run(String... strings) throws Exception {
        /* 缓存光源相关数据成功 */
        try{
            DBUtilTool.getInstance().getAll();
            LogUtil.info("光源相关数据初始化成功！");
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.error("光源相关数据初始化异常:"+e.getMessage());
        }
        /* 缓存系统参数 */
        //initFORMULAToModel(formulaService.findList());
    }
    /**
     * @description 初始化配方到内存中
     * @author: fangyong
     * @date 2018/11/17 10:40
     */
    private static void initFORMULAToModel(List<FormulaModel> list) {
       modelList=list;
        if (list != null && !list.isEmpty()) {
            for (FormulaModel model : list) {
                initData(model);
            }
        }
    }

    /**
     * @description 配方处理
     * @author: fangyong
     * @date 2018/11/17 10:40
     */
    private static synchronized List initData(FormulaModel model) {
        Map<String, Object> map=new HashMap();
        map.put("formulaCount",model.getFormulaCount());
        map.put("id",model.getId());
        map.put("dyeGroupId",(model.getDyeGroup() != null ? model.getDyeGroup().getId() :""));
        map.put("materialId",(model.getMaterial() != null ? model.getMaterial().getId() : ""));
        map.put("type",model.getType());
        map.put("L",(model.getColorant() != null ? model.getColorant().getL() :""));
        map.put("A",(model.getColorant() != null ? model.getColorant().getA() :""));
        map.put("B",(model.getColorant() != null ? model.getColorant().getB() :""));
        list.add(map);
        return list;
    }

    private    static  List<FormulaModel> modelList=new ArrayList();
    private   static  List<Map<String, Object>> list=new ArrayList();
    public synchronized static void   showInitData(){
        synchronized (Init.class){
            if (list != null&&!list.isEmpty()) {
                for (Map<String, Object> map:list) {
                    System.out.println("id:"+map.get("id")+"  dyeGroupId:"+map.get("dyeGroupId")+"    formulaCount:"+map.get("formulaCount")+"   materialId:"+map.get("materialId")+
                            "   type:"+map.get("type")+"   L:"+map.get("L")+"   A:"+map.get("A")+"   B:"+map.get("B"));
                }
            }
        }

    }

    public synchronized static List<FormulaModel>  showInitmodelList(){
                  return modelList;

    }
}
