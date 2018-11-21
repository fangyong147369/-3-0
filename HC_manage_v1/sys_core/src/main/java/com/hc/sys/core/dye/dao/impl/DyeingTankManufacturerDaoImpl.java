package com.hc.sys.core.dye.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeingTankManufacturerDao;
import com.hc.sys.core.dye.entity.DyeingTankManufacturer;
import com.hc.sys.core.dye.model.DyeingTankManufacturerModel;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:17
 * @Version: 1.0.0.0
 */
@Repository
public class DyeingTankManufacturerDaoImpl extends BaseDaoImpl<DyeingTankManufacturer> implements DyeingTankManufacturerDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<DyeingTankManufacturer> list(DyeingTankManufacturerModel model) {
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
