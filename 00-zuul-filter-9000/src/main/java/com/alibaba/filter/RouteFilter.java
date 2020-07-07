package com.alibaba.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author shenmeng
 * @Date 2020/7/2
 **/
@Component
public class RouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //指定路由之前过滤
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //在系统最小值-3之前，数越小优先级越高
        return -5;
    }

    /**
     * 对请求进行过滤的核心方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //获取当前请求的上下文
        RequestContext context = RequestContext.getCurrentContext();
        //从请求上下文中获取当前请求信息
        HttpServletRequest request = context.getRequest();
        String user = request.getParameter("user");
        String uri = request.getRequestURI();
        if(uri.contains("/abc8080") && StringUtils.isEmpty(user)){
            //表示当前请求未通过过滤，默认值为true
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        //返回true，run方法将会调用
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("通过过滤");
        return null;
    }
}
