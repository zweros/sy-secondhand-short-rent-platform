package com.szxy.web.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther:zwer
 * @Date:2019/9/1 9:43
 * @Description:com.szxy.web.filter
 * @Version:1.0
 **/

/**
 *    错误过滤器
 *    当其他过滤器出现异常时，调用该过滤器
 *
 */
@Component   //纳入 Spring IOC 容器中
public class ErrorFilter extends ZuulFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(ErrorFilter.class);
    /**
     *  过滤内容：在 run 方法编写过滤逻辑
     */
    @Override
    public Object run() {
        logger.info("=============== error ================");
        return null;
    }

    /**
     *  是否开启该Filter，true 表示开启，false 表示不开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 表示该 Filter 的优先级，通过返回的整数值
     * 值越小，表示优先级越高
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 过滤器类型：通过过滤器类型决定了过滤器执行的时间
     */
    @Override
    public String filterType() {
        return "error";
    }

}