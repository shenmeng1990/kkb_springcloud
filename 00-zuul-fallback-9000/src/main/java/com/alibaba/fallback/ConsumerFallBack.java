package com.alibaba.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author shenmeng
 * @Date 2020/7/2
 **/
@Component
public class ConsumerFallBack implements FallbackProvider {
    @Override
    public String getRoute() {
        //对指定的微服务进行降级
        //return "depart-consumer-zuul-8080";
        //指定对所有微服务进行降级
        return "*";
    }

    /**
     * @param route 请求中的微服务名称
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        //如果不是depart-consumer-zuul-8080，则不降级
      /*  if(!"depart-consumer-zuul-8080".equals(route)){
            return null;
        }*/
        //仅对depart-consumer-zuul-8080进行服务降级
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String msg = "fallback:"+route;
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
