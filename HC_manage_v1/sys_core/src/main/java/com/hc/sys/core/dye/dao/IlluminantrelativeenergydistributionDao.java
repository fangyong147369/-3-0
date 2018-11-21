package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Illuminantrelativeenergydistribution;
import com.hc.sys.core.dye.model.IlluminantrelativeenergydistributionModel;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:20
 * @Version: 1.0.0.0
 */
public interface IlluminantrelativeenergydistributionDao extends BaseDao<Illuminantrelativeenergydistribution> {

        /**
         * 列表
         * @param model
         * @return
         */
        PageDataList<Illuminantrelativeenergydistribution> list(IlluminantrelativeenergydistributionModel model);

        }