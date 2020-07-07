package com.alibaba.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.api.RibbonFilterContext;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author shenmeng
 * @Date 2020/7/2
 **/
//@Component
public class GrayFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //获取指定的请求头信息，该头信息在浏览器提交请求时携带，用于区分该请求路由到哪个主机
        String mark = request.getHeader("gray-mark");
        //默认将请求路由到running-host上
        RibbonFilterContextHolder.getCurrentContext().add("host-mark","running-host");
        if (StringUtils.isNotEmpty(mark) && "enable".equals(mark)) {
            //若gray-mark的值为enable的话，则请求路由到gray-host
            RibbonFilterContextHolder.getCurrentContext().add("host-mark","gray-host");
        }
        return null;
    }
}
