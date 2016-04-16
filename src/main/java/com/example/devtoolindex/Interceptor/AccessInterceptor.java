package com.example.devtoolindex.Interceptor;

import com.example.devtoolindex.db.entity.IPStatEntity;
import com.example.devtoolindex.db.service.IPStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by hongkailiu on 2016-04-15.
 */
@Slf4j public class AccessInterceptor implements HandlerInterceptor {
    @Autowired IPStatService ipStatService;

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        log.debug("preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        log.debug("postHandle");
    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion");
        String ip = request.getRemoteAddr();
        log.debug("ip" + ip);
        Date date = new Date();
        String path = request.getRequestURI();
        log.debug("path" + path);

        IPStatEntity ipStatEntity = new IPStatEntity(ip, date, path);
        ipStatService.save(ipStatEntity);
    }
}
