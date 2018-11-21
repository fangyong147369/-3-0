package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import java.util.List;
/**
 * @Description: 染缸厂商
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:15
 * @Version: 1.0.0.0
 */
public interface DyeingTankManufacturerDao extends BaseDao<DyeingTankManufacturer> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<DyeingTankManufacturer> list(DyeingTankManufacturerModel model);
}
