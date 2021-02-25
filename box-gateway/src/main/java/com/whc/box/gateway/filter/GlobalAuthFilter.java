package com.whc.box.gateway.filter;

import com.whc.box.common.api.ResultCode;
import com.whc.box.common.exception.BoxGatewayException;
import com.whc.box.gateway.properties.NotAuthUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@EnableConfigurationProperties(value = NotAuthUrlProperties.class)
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private NotAuthUrlProperties notAuthUrlProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String currentUrl = exchange.getRequest().getURI().getPath();
        //1:不需要认证的url
        if (shouldSkip(currentUrl)) {
            log.info("跳过认证的URL:{}", currentUrl);
            return chain.filter(exchange);
        }
        log.info("需要认证的URL:{}", currentUrl);

        //第一步:解析出我们Authorization的请求头  value为: “bearer XXXXXXXXXXXXXX”
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        //第二步:判断Authorization的请求头是否为空
        if(StringUtils.isEmpty(authHeader)) {
            log.warn("需要认证的url,请求头为空");
            throw new BoxGatewayException(ResultCode.AUTHORIZATION_HEADER_IS_EMPTY);
        }

        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 方法实现说明:不需要授权的路径
     */
    private boolean shouldSkip(String currentUrl) {
        //路径匹配器(简介SpringMvc拦截器的匹配器)
        //比如/oauth/** 可以匹配/oauth/token    /oauth/check_token等
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String skipPath : notAuthUrlProperties.getShouldSkipUrls()) {
            if (pathMatcher.match(skipPath, currentUrl)) {
                return true;
            }
        }
        return false;
    }
}
