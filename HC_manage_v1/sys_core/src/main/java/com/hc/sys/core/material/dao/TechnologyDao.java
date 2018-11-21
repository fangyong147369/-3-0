package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.Technology;
import com.hc.sys.core.material.model.TechnologyModel;
import org.codehaus.jackson.map.Serializers;

import java.util.List;

/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:45
 * @Version: 1.0.0.0
 */
public interface TechnologyDao extends BaseDao<Technology> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Technology> list(TechnologyModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<Technology> findAll();
}
