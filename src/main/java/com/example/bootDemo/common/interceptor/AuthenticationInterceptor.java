package com.example.bootDemo.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.bootDemo.common.annotation.auth.NoAuth;
import com.example.bootDemo.common.consts.CodeMsg;
import com.example.bootDemo.common.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 认证拦截
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        try {
            response.setCharacterEncoding("utf-8");
            if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
                PrintWriter out = response.getWriter();
                out.print(true);
                out.flush();
                out.close();
                return false;
            }
            log.info("收到请求: {}",request.getRequestURL());
            // 如果不是映射到方法直接通过
            if (!(object instanceof HandlerMethod)) {
                return true;
            }
            log.info("contentType: {}", request.getContentType());
            //文件上传跳过
            if (request.getContentType() != null && request.getContentType().startsWith("multipart")) {
                return true;
            }

            HandlerMethod handlerMethod = (HandlerMethod) object;
            Method method = handlerMethod.getMethod();

            //从url query中获取token
            String token = getToken(request);

            //检查是否有passToken注释，有则跳过认证
            NoAuth passToken = method.getAnnotation(NoAuth.class);
            if (passToken != null && passToken.required()) {
                return true;
            }
            checkToken(token);
            return true;
        } catch (Exception e) {
            log.error("token校验,异常，{}", e);
            return errMsg(CodeMsg.INVALID_TOKEN, response);
        }
    }

    private void checkToken(String token) {
        log.info("验证token : {}", token);
        // TODO: 2019/12/9 验证token
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    private boolean errMsg(int code, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(JSONObject.toJSONString(Result.error(code)));
        out.flush();
        out.close();
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            //从header获取token
            token = request.getHeader("token");
        }
        return token;
    }
}