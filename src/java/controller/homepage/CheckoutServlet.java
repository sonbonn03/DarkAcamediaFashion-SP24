package controller.homepage;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Product;

public class CheckoutServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> list = productDAO.findAll();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, list);
        request.setAttribute("size", cart.getList().size());
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("views/user/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        Account acount = null;
        Object a = session.getAttribute("account");
        if (a != null) {
            String txt = "";
            Cookie[] c = request.getCookies();
            if (c != null) {
                for (Cookie o : c) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }
            List<Product> list = productDAO.findAll();
            Cart cart = new Cart(txt, list);

            acount = (Account) a;
            OrderDAO odb = new OrderDAO();
            odb.addOrder(name, phone, address, note, acount, cart);
            response.sendRedirect("home");
            return;
        } else {
            response.sendRedirect("login?action=login");
        }

    }

}
