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
        String clientName=req.getParameter("clientName");
        String clientAddress=req.getParameter("clientAddress");
        String clientBrowser=req.getHeader("User-agent");
        String clientIPAddress=req.getRemoteAddr();
        String size= (String) req.getSession().getAttribute("size");
        String color= (String) req.getSession().getAttribute("color");

        setAttributesInSession(req.getSession(), clientName, clientAddress, clientBrowser, clientIPAddress);

        setVariablesInWebContext(webContext, clientName, clientAddress, clientBrowser, clientIPAddress, size, color);

        springTemplateEngine.process("confirmationInfo.html",webContext,resp.getWriter());
    }

    private void setVariablesInWebContext(WebContext webContext, String clientName, String clientAddress, String clientBrowser, String clientIPAddress, String size, String color) {
        webContext.setVariable("clientName", clientName);
        webContext.setVariable("clientAddress", clientAddress);
        webContext.setVariable("clientIPAddress", clientIPAddress);
        webContext.setVariable("clientBrowser", clientBrowser);
        webContext.setVariable("color", color);
        webContext.setVariable("size", size);
    }

    private void setAttributesInSession(HttpSession session, String clientName, String clientAddress, String clientBrowser, String clientIPAddress) {
        session.setAttribute("clientName", clientName);
        session.setAttribute("clientAddress", clientAddress);
        session.setAttribute("clientBrowser", clientBrowser);
        session.setAttribute("clientIPAddress", clientIPAddress);
    }
}
