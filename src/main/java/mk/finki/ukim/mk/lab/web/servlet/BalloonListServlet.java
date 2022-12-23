package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "balloon-list-servlet", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final OrderService orderService;
    private final ManufacturerService manufacturerService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService,
                              OrderService orderService,
                              ManufacturerService manufacturerService,
                              SpringTemplateEngine springTemplateEngine)
    {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.manufacturerService = manufacturerService;
        this.springTemplateEngine = springTemplateEngine;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("manufacturers",manufacturerService.findAll());
        context.setVariable("orders",orderService.listAll());
        context.setVariable("balloons",balloonService.listAll());
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }


}
