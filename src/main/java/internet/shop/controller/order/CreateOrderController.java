package internet.shop.controller.order;

import internet.shop.controller.user.LoginController;
import internet.shop.lib.Injector;
import internet.shop.model.Order;
import internet.shop.model.ShoppingCart;
import internet.shop.service.OrderService;
import internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("internet.shop");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Order order = orderService.completeOrder(shoppingCart);
        resp.sendRedirect(req.getContextPath() + "/user/orders/all");
    }
}
