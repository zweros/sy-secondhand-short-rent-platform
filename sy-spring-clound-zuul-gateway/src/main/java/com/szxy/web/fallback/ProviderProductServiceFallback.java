package com.szxy.web.fallback;

/**
 * @Auther:zwer
 * @Date:2019/9/1 9:47
 * @Description:com.szxy.web.fallback
 * @Version:1.0
 **/

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 *   对服务做降级处理
 *
 */
@Component
public class ProviderProductServiceFallback implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return null;
    }

    /**
     *   product service 发生异常，不可使用
     *       对客户端做的响应
     */
    @Override
    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse() {

            /**
             * 设置响应头信息
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders header = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("utf-8") );
                header.setContentType(mediaType);
                return header;
            }

            /**
             * 设置响应体
             */
            @Override
            public InputStream getBody() throws IOException {
                String body = "商品服务不可用，请与管理员联系！";
                return new ByteArrayInputStream(body.getBytes());
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().toString();
            }

            /**
             * 设置状态码
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;//200
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public void close() {

            }
        };
    }

}