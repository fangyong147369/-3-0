package com.hc.sys.core.dye.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.IlluminantDao;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.model.IlluminantModel;
import org.springframework.stereotype.Repository;

/**
 * @Description:颜色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:06
 * @Version: 1.0.0.0
 */
@Repository
public class IlluminantDaoImpl extends BaseDaoImpl<Illuminant> implements IlluminantDao {
    /**
     * 列表
     * @param model
     * @return
     */
    @Override
    public PageDataList<Illuminant> list(IlluminantModel model) {
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
