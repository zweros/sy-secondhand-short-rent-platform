package com.szxy.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Auther:zwer
 * @Date:2019/8/28 20:03
 * @Description:com.szxy.web.listener
 * @Version:1.0
 **/
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private final Logger logger =
            LoggerFactory.getLogger(ServletContextListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("服务器开启中 ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.getSessionCookieConfig().setMaxAge(0);
        logger.info("服务器关闭中  ...");
    }
}
