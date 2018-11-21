package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import com.hc.sys.core.dye.model.IlluminantwhitexyzModel;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:15
 * @Version: 1.0.0.0
 */
public interface IlluminantwhitexyzDao extends BaseDao<Illuminantwhitexyz> {

    /**
     * 列表
     * @param model
     * @return
     */
    PageDataList<Illuminantwhitexyz> list(IlluminantwhitexyzModel model);

}
