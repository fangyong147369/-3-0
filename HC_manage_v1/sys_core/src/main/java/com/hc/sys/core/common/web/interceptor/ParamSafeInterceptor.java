package com.hc.sys.core.common.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.core.manage.model.RoleModel;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import java.lang.reflect.Method;
/**
 * @description 拦截器
 * @author: fangyong
 * @date 2018/11/16 13:37
 */
@Component
public class ParamSafeInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前进行调用（Controller方法调用之前）
        debugLog(request, handler);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
        // 初始化数据
//        LogUtil.info("production");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
//        LogUtil.info("默认");
    }

    private void debugLog(HttpServletRequest request, Object handler) {
        Thread.currentThread().getStackTrace();
        try {
            Class clazz = ((HandlerMethod) handler).getBean().getClass();
            Method m = ((HandlerMethod) handler).getMethod();
            StackTraceElement traces =new StackTraceElement(clazz.getName(), m.getName(), clazz.getName(), 1);
            StringBuilder sb = new StringBuilder();
            sb.append("\n---------------------------------------------------------------------------------\n                   ")
                    .append(traces)
                    .append("\n          方式:     ")
                    .append(request.getMethod())
                    .append("\n          方法:     ")
                    .append(m.getName())
                    .append("\n          参数:     ");
            request.getParameterMap().forEach((k, v) -> sb.append(k + "=" + v[0] + " "));
            sb.append("\n---------------------------------------------------------------------------------");
            LogUtil.info(sb.toString());
        } catch (Exception e) {
                LogUtil.error("请求方式:"+request.getMethod()+"   接口请求方式不被支持或接口不存在！"+e.getMessage());
        }
    }
}
