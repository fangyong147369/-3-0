package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:12
 * @Version: 1.0.0.0
 */
public interface IlluminantobserverxyzDao extends BaseDao<Illuminantobserverxyz> {

        /**
         * 列表
         * @param model
         * @return
         */
        PageDataList<Illuminantobserverxyz> list(IlluminantobserverxyzModel model);

        }