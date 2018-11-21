package com.hc.sys.core.dye.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.IlluminantwhitexyzDao;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import com.hc.sys.core.dye.model.IlluminantwhitexyzModel;
import org.springframework.stereotype.Repository;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:16
 * @Version: 1.0.0.0
 */
@Repository
public class IlluminantwhitexyzDaoImpl extends BaseDaoImpl<Illuminantwhitexyz> implements IlluminantwhitexyzDao {
    /**
     * 列表
     * @param model
     * @return
     */
    @Override
    public PageDataList<Illuminantwhitexyz> list(IlluminantwhitexyzModel model) {
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
