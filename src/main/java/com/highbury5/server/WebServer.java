package com.highbury5.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {

    private Log log = LogFactory.getLog(this.getClass());

    public void start(){
        log.info("jetty start...");

        Server server = new Server(80);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("./web/WebRoot/WEB-INF/web.xml");
        context.setResourceBase("./web/WebRoot");
        server.setHandler(context);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
