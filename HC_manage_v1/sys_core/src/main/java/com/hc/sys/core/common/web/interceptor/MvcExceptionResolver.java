package com.hc.sys.core.common.web.interceptor;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.util.json.JsonUtil;
import com.hc.sys.common.util.log.LogUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * @Description:  mvc异常处理器
 * @Author: fangyong
 * @CreateDate: 2018/10/27 16:00
 * @Version: 1.0.0.0
 */
@Component
public class MvcExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            boolean isAjax = "POST".equals(request.getMethod())||"GET".equals(request.getMethod());
            if (isAjax) {
                response.setContentType("application/json;charset=UTF-8");
                Result result=Result.error();
                result.setMessage("接口请求方式不被支持或接口不存在");
                response.getWriter().write(JsonUtil.formatToStr(result));
                return new ModelAndView();
            }
        } catch (IOException e) {
            LogUtil.error(ExceptionUtils.getStackTrace(e)+"----"+e.getMessage());
        }
        return new ModelAndView();

    }

}
