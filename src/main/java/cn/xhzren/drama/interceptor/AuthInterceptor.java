package cn.xhzren.drama.interceptor;

import cn.xhzren.drama.utils.Result;
import cn.xhzren.drama.utils.StatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader("Authorization") == null) {
            response.getWriter().write(Result.res(StatusCodeEnum.NOT_AUTHENTICATED));
            return false;
        }
        if(redisTemplate.opsForValue().get(request.getHeader("Authorization")) == null) {
            response.getWriter().write(Result.res(StatusCodeEnum.NOT_AUTHENTICATED));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
        System.out.println(response.getCharacterEncoding());
    }
}
