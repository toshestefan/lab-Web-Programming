package mk.finki.ukim.mk.lab.web.servlet;


import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "select-balloon-servlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String color=req.getParameter("color");
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        webContext.setVariable("color",color);
        req.getSession().setAttribute("color",color);
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("selectBalloonSize.html",webContext,resp.getWriter());
    }
}
