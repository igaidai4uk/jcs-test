package com.jcs.example;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CachingService service;
    private Timer timer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        service = CachingService.getInstance();
        MetricRegistry metrics = (MetricRegistry) getServletContext().getAttribute("com.codahale.metrics.servlets.MetricsServlet.registry");
        timer = metrics.timer(MetricRegistry.name(getClass(), "service"));
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        for (int i = 0; i < Book.TOTAL; i++) {
            Timer.Context context = timer.time();

            service.get(i);

            context.stop();
        }

        stats(response.getWriter());
    }

    public void stats(PrintWriter writer) {
        CachingService.showStats(writer);
    }

}