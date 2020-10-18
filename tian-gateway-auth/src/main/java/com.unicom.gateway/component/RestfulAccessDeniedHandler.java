package com.unicom.gateway.component;

import cn.hutool.json.JSONUtil;
import com.unicom.common.api.ResultUtils;
import java.nio.charset.Charset;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
/**
 *
 * @Description 自定义返回结果：没有权限访问异常
 * @param
 * @return
 * @date 2020/9/2
 * @author ctf
 **/

@Component
public class RestfulAccessDeniedHandler implements ServerAccessDeniedHandler {

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
    ServerHttpResponse response=exchange.getResponse();
    response.setStatusCode(HttpStatus.OK);
    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    response.getHeaders().set("Access-Control-Allow-Origin","*");
    response.getHeaders().set("Cache-Control","no-cache");
    String body= JSONUtil.toJsonStr(ResultUtils.forbidden(e.getMessage()));
    DataBuffer buffer=response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
    return  response.writeWith(Mono.just(buffer));
  }
}