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

public class UpdateCacheServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CachingService service;
    private Timer timer;
    private Timer timer2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        service = CachingService.getInstance();
        MetricRegistry metrics = (MetricRegistry) getServletContext().getAttribute("com.codahale.metrics.servlets.MetricsServlet.registry");
        timer = metrics.timer(MetricRegistry.name(getClass(), "remove"));
        timer2 = metrics.timer(MetricRegistry.name(getClass(), "update"));
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        for (int i = 0; i < Book.TOTAL/2; i++) {
            Timer.Context context = timer.time();
            service.remove(i);
            context.stop();
        }

        for (int i = Book.TOTAL/2; i < Book.TOTAL; i++) {
            Timer.Context context = timer2.time();

            service.put(new Book("Bla " + i, "Author " + i, i));

            context.stop();
        }

        stats(response.getWriter());
    }

    public void stats(PrintWriter writer) {
        CachingService.showStats(writer);
    }
}