package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.CartService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-order-servlet",urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CartService cartService;


    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine,CartService cartService) {
        this.springTemplateEngine = springTemplateEngine;
        this.cartService = cartService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        webContext.setVariable("color",req.getSession().getAttribute("color"));
        webContext.setVariable("size",req.getParameter("size"));
        webContext.setVariable("carts",cartService.findAll());
        req.getSession().setAttribute("size",req.getParameter("size"));
        springTemplateEngine.process("deliveryInfo.html",webContext,resp.getWriter());
    }
}
