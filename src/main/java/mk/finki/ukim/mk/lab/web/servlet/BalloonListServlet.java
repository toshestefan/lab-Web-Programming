package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "balloon-list-servlet", urlPatterns = "/")
public class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService, OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());

        context.setVariable("orders",orderService.listAll());
        context.setVariable("balloons",balloonService.listAll());
        springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }


}
