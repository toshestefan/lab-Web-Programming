package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="order-servlet", urlPatterns = "/orderToCart")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService;

    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s=req.getSession();
        User user = (User) s.getAttribute("user");
        orderService.placeOrder(
                (String) s.getAttribute("color"),
                (String) s.getAttribute("size"), (String) s.getAttribute("cart"));
        resp.sendRedirect("/balloons");
    }
}
