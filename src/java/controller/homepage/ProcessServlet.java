package controller.homepage;

import dal.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

public class ProcessServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        String quantity_raw = request.getParameter("quantity");
        System.out.println(id_raw);
        int quantity, id = 0;
        List<Product> listProduct = productDAO.findAll();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    txt += c.getValue();
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        Cart cart = new Cart(txt, listProduct);
        try {
            quantity = Integer.parseInt(quantity_raw);
            id = Integer.parseInt(id_raw);
            if (quantity == 0) {
                cart.removeItem(id);
            } else {
                cart.getItemByID(id).setQuantity(quantity);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Item> items = cart.getList();
        txt = "";
        if (items.size() > 0) {
            txt = items.get(0).getProduct().getId() + ":"
                    + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                txt += "/" + items.get(i).getProduct().getId() + ":"
                        + items.get(i).getQuantity();
            }
        }
        Cookie cookie = new Cookie("cart", txt);
        cookie.setMaxAge(2 * 60 * 60 * 24);
        response.addCookie(cookie);
        request.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
