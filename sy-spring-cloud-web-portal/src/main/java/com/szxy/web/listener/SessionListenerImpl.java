package com.szxy.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Auther:zwer
 * @Date:2019/8/28 20:43
 * @Description:com.szxy.web.listener
 * @Version:1.0
 **/
public class SessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext sc =  se.getSession().getServletContext();

    }
}
