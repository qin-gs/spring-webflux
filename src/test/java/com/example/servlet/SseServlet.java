package com.example.servlet;

import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * server sent event
 */
@WebServlet("/sse")
public class SseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置这两个值，多次返回数据
        resp.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        resp.setContentType(StandardCharsets.UTF_8.displayName());

        for (int i = 0; i < 5; i++) {
            // 指定事件标志
            resp.getWriter().write("event: myEvent\n");
            // 两个 \n
            resp.getWriter().write("data: " + i + "\n\n");
            resp.getWriter().flush();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
