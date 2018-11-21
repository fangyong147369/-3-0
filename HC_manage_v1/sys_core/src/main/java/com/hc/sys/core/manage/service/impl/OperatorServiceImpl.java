package com.hc.sys.core.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.http.RequestUtil;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.core.manage.dao.OperatorDao;
import com.hc.sys.core.manage.dao.RoleDao;
import com.hc.sys.core.manage.entity.Role;
import com.hc.sys.core.manage.model.RoleModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.util.encrypt.MD5;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.manage.service.OperatorService;

/**
 * 管理员
 *
 * @Author: fangyong
 * @CreateDate: 2018/10/18 13:46
 * @Version: 1.0.0.0
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private OperatorDao operatorDao;
    @Resource
    private RoleDao roleDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OperatorModel model) {
        PageDataList<Operator> pageDataList = operatorDao.list(model);
        PageDataList<OperatorModel> pageDataList_ = new PageDataList<OperatorModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OperatorModel> list = new ArrayList<OperatorModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Operator operator : pageDataList.getList()) {
                OperatorModel model_ = OperatorModel.instance(operator);
                model_.setRoleModel(RoleModel.instance(operator.getRole()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(OperatorModel model) {
        this.validParam(model);//校验参数
        this.setAddParamadd(model);//设置修改基本参数
        Operator operator = model.prototype();
        operatorDao.save(operator);
        operatorDao.clear();
        return Result.success().setData(operator);
    }


    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(OperatorModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Operator operator = operatorDao.find(model.getId());
        this.validParam(model);//校验参数
        this.setUpdateParam(model);//设置修改基本参数
        model.setPassword(operator.getPassword());
        model.setAddTime(operator.getAddTime());
        model.setSessionID(operator.getSessionID());
        model.setLoginIp(operator.getLoginIp());
        model.setLoginTime(operator.getLoginTime());
        operator = model.prototype();
        operatorDao.update(operator);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Transactional
    @Override
    public Result getById(OperatorModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Operator operater = operatorDao.find(model.getId());
        if (operater == null) {
            return Result.error("此操作员不存在！");
        }
        OperatorModel model_ = OperatorModel.instance(operater);
        model_.setRoleModel(RoleModel.instance(operater.getRole()));
        return Result.success().setData(model_);
    }

    /**
     * 登录
     *
     * @param model
     * @return
     */
    public Result signIn(OperatorModel model) {
        OperatorModel model_ = null;
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("userName", model.getUserName());
        queryParam.addParam("password", MD5.toMD5(model.getPassword()));
        Operator operators = operatorDao.findByCriteriaForUnique(queryParam);
        if (null == operators) {
            LogUtil.error("用户名或密码错误！");
            return Result.error("用户名或密码错误！");
        } else {
            if (operators.getStatus() == -1) {
                LogUtil.error("此用户已被禁用！");
                return Result.error("此用户已被禁用！");
            }
            operators.setPassword(operators.getPassword());
            operators.setLoginIp(RequestUtil.getClientIp());
            operators.setLoginTime(DateUtil.getNow());
            //web-登录用
            if(model.getSignInWay()==2){
                model.setId(operators.getId());
                Result result=(Result)this.selectOperatorSessionIDIsNull(model);
                if(result.getCode()==-1){
                    return Result.error("您已经在其它地方登录了!");
                }else{
                    if(!StringUtil.isBlank(model.getSessionID())){
                        operators.setSessionID(model.getSessionID());
                    }
                }
            }
            operatorDao.update(operators);
            model_ = OperatorModel.instance(operators);
            model_.setRoleModel(RoleModel.instance(operators.getRole()));
        }
        return Result.success().setData(model_);
    }
    /**
     * @description 查询SessionID是否为空
     * @author: fangyong
     * @date 2018/11/15 11:23
     */
    @Transactional
    @Override
    public Object selectOperatorSessionIDIsNull(OperatorModel model) {
     return operatorDao.selectOperatorSessionIDIsNull(model);
    }
    /**
     * 登录ByNetty
     *
     * @param model
     * @return
     */
    @Override
    public Result signInByNetty(OperatorModel model) {
        checkSigInParams(model);
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("userName", model.getUserName());
        queryParam.addParam("password", MD5.toMD5(model.getPassword()));
        Operator operators = operatorDao.findByCriteriaForUnique(queryParam);
        if (null == operators) {
            LogUtil.error("用户名或密码错误！");
            return Result.error("用户名或密码错误！");
        }
        return Result.success().setData(operators);
    }

    @Transactional
    @Override
    public Object deleteSessionID(OperatorModel model) {
        if (StringUtil.isBlank(model.getId())||model.getId()<=0) {
            return Result.error("参数错误----请检查id参数是否正确！");
        }
        int n = operatorDao.deleteSessionID(model);
        if (n != 1) {
            return Result.error("操作失败");
        }
        return Result.success();
    }

    @Transactional
    @Override
    public Object selectOperatorSessionID(OperatorModel model) {
        return operatorDao.selectOperatorSessionID();
    }

    /**
     * 通过条件查询Operator
     *
     * @param model
     * @return
     */
    @Transactional
    @Override
    public Result getOperatorBySearchName(OperatorModel model) {
        if (!StringUtil.isNotBlank(model.getSearchName())) {
            return Result.error("参数不能为空！");
        }
        Operator operator = operatorDao.getOperatorBySearchName(model);
        if (operator == null) {
            return Result.error("此管理员不存在！");
        }
        return Result.success().setData(operator);
    }

    /**
     * @description 公共检验登录参数
     * @author: fangyong
     * @date 2018/11/1 20:07
     */
    public void checkSigInParams(OperatorModel model) {
        if (!StringUtil.isNotBlank(model.getUserName())) {
            LogUtil.error("用户名不能为空！");
            Result.error().setMessage("用户名不能为空！");

        }
        if (!StringUtil.isNotBlank(model.getPassword())) {
            LogUtil.error("密码不能为空！");
            Result.error().setMessage("密码不能为空！");
        }
    }

    /**
     * 参数校验
     */
    public void validParam(OperatorModel model) {
        if (StringUtil.isBlank(model.getUserName())) {
            Result.error().setMessage("用户名不能为空！");
        }
        if (StringUtil.isBlank(model.getEmail())) {
            Result.error().setMessage("Email不能为空！");
        }
        if (model.getPwdWay() == 1) {
            if (StringUtil.isBlank(model.getPassword())) {
                Result.error().setMessage("登录密码不能为空！");
            }
        }
        if (StringUtil.isBlank(model.getMobile())) {
            Result.error().setMessage("电话不能为空！");
        }
        if (model.getRoleId() <= 0) {
            Result.error().setMessage("角色ID错误错误！");
        } else {
            Role role = roleDao.find(model.getRoleId());
            if (null == role) {
                Result.error().setMessage("查询角色不存在！");
            }
            model.setRole(role);
        }
    }

    /**
     * 设置修改基本参数
     *
     * @param
     */
    public void setAddParamadd(OperatorModel model) {
        model.setPassword(MD5.toMD5(model.getPassword()));
        model.setRole(model.getRole());
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 设置修改基本参数
     *
     * @param
     */
    public void setUpdateParam(OperatorModel model) {
        if (model.getPwdWay() == 2) {
            if (StringUtil.isNotBlank(model.getPassword())) {
                model.setPassword(MD5.toMD5(model.getPassword()));
            } else {
                model.setPassword(model.getPassword());
            }

        }
        model.setLoginIp(model.getLoginIp());
        model.setLoginTime(model.getLoginTime());
        model.setMobile(model.getMobile());
        model.setRole(model.getRole());
        model.setEmail(model.getEmail());
        model.setUserName(model.getUserName());
        model.setStatus(model.getStatus());
        model.setRemark(model.getRemark());
        model.setUpdateTime(DateUtil.getNow());
    }

}