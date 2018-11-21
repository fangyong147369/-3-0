package com.hc.sys.core.common.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.exception.NoRollBackException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.util.WebUtils;
/**
 * @description 异常中心控制器
 * @author: fangyong
 * @date 2018/11/16 13:37
 */
@Controller
public class ExceptionCenterController  implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
		LogUtil.error("---------------系统异常信息-----------------------"+ex.getMessage()+""+obj.getClass().getName());
		WebUtils.responseJson(response, Result.error(ex.getMessage()));
    	if (ex instanceof BusinessException || ex instanceof NoRollBackException) {
			LogUtil.error("---------------自定义异常信息-----------------------"+ex.getMessage());
    		WebUtils.responseJson(response, Result.error(ex.getMessage()));
    	}
		return null;
    }
    
}
