package mk.finki.ukim.mk.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "confirmaton-info-servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet  extends HttpServlet {

     private final SpringTemplateEngine springTemplateEngine;


    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        String clientBrowser=req.getHeader("User-agent");
        String clientIPAddress=req.getRemoteAddr();
        String size= (String) req.getSession().getAttribute("size");
        String color= (String) req.getSession().getAttribute("color");
        String cart=req.getParameter("cart");
        resp.setContentType("application/xhtml+xml");
        setAttributesInSession(req.getSession(), clientBrowser, clientIPAddress,cart);

        setVariablesInWebContext(webContext, clientBrowser, clientIPAddress, size, color,cart);

        springTemplateEngine.process("confirmationInfo.html",webContext,resp.getWriter());
    }

    private void setVariablesInWebContext(WebContext webContext, String clientBrowser, String clientIPAddress, String size, String color, String cart) {
        webContext.setVariable("clientIPAddress", clientIPAddress);
        webContext.setVariable("clientBrowser", clientBrowser);
        webContext.setVariable("color", color);
        webContext.setVariable("size", size);
        webContext.setVariable("cart" ,cart);
    }

    private void setAttributesInSession(HttpSession session, String clientBrowser, String clientIPAddress, String cart) {
        session.setAttribute("clientBrowser", clientBrowser);
        session.setAttribute("clientIPAddress", clientIPAddress);
        session.setAttribute("cart",cart);
    }
}
