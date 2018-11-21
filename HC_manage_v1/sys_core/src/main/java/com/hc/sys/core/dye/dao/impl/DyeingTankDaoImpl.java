package com.hc.sys.core.dye.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeManufacturerDao;
import com.hc.sys.core.dye.dao.DyeingTankDao;
import com.hc.sys.core.dye.entity.DyeManufacturer;
import com.hc.sys.core.dye.entity.DyeingTank;
import com.hc.sys.core.dye.model.DyeManufacturerModel;
import com.hc.sys.core.dye.model.DyeingTankModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 染缸
 * @Author: fangyong
 * @CreateDate: 2018/10/19 20:56
 * @Version: 1.0.0.0
 */
@Repository
public class DyeingTankDaoImpl extends BaseDaoImpl<DyeingTank> implements DyeingTankDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<DyeingTank> list(DyeingTankModel model) {
        QueryParam param = QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}
