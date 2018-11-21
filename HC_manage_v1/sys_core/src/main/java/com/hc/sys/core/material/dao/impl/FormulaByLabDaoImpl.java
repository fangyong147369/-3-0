package com.hc.sys.core.material.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.core.material.dao.FormulaByLabDao;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.entity.FormulaByLab;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Description: 配方查询
 * @Author: fangyong
 * @CreateDate: 2018/11/17 16:44
 * @Version: 1.0.0.0
 */
@Repository
public class FormulaByLabDaoImpl extends BaseDaoImpl<FormulaByLab> implements FormulaByLabDao {
    @Override
    public List<FormulaByLab> all() {
        int pageNo=0;
        int pageSize=10;
        StringBuilder sql=new StringBuilder("SELECT m.*,mm.flag FROM message m LEFT JOIN message_member mm ON mm.message_id=m.id WHERE mm.member_id=? AND m.type=? ORDER BY mm.message_id DESC  limit ?,?");
        StringBuilder sb=new StringBuilder();
        sb.append("select f.id as pid,f.name as pname,fbl.key_id as keyId,fbl.formula_id as formulaId from formula f  left join  formula_by_lab fbl on f.id=fbl.formula_id  limit "+pageNo+","+pageSize);
        Query query=em.createNativeQuery(sb.toString());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        int size= query.getResultList().size();
        System.out.println("size==="+size);
        List list = query.getResultList();
        List<FormulaByLab> rsList=new ArrayList<FormulaByLab>();
        List<Formula> rsLists=new ArrayList<Formula>();
        for (Object object : query.getResultList()) {
            Map map=(Map)object;
            System.out.println("pid="+map.get("pid")+"   pname="+map.get("pname")+"   keyId="+map.get("keyId")+"   formulaId="+map.get("formulaId"));
        }
        return rsList;
    }
}
