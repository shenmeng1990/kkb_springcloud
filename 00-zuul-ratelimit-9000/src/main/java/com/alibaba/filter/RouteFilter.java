package com.alibaba.filter;

import com.google.common.util.concurrent.RateLimiter;
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

    //创建一个令牌桶，每秒生成两个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

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
        //若可以立即获取到一个令牌，则返回true，否则返回false
        //RATE_LIMITER.tryAcquire(5,3,TimeUnit.SECONDS) 若3秒钟之内获取5个令牌则返回true，否则返回false，不阻塞
        //RATE_LIMITER.acquire() 获取1个令牌，若获取不到则阻塞，直到获取到为止
        if(!RATE_LIMITER.tryAcquire()){
            context.setSendZuulResponse(false);
            //向客户端响应请求数量太多
            context.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("通过过滤");
        return null;
    }
}
