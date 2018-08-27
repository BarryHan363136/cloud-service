package com.barry.cloud.platform.security.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限不足的返回值
 *
 * @author Tongshan.Han@partner.bmw.com
 * @date 2018/8/27 18:05
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(403);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden");
    }

}