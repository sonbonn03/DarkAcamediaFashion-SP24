package controller.homepage;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */

public class ProductDetailServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get ve id product
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        String url ="product-detail?id=" + id_raw;
        HttpSession session = request.getSession();
        session.setAttribute("stayPage", url);
        Product product = new Product();
        product.setId(id);
        //lay product tu database
        Product ProductFoundId = productDAO.findById(product);
        //set product vao req va chuyen sang detail.jsp
        request.setAttribute("ProductFoundId", ProductFoundId);
        //Cart
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie x : arr) {
                if (x.getName().equals("cart")) {
                    txt += x.getValue();
                }
            }
        }
        List<Product> list = productDAO.findAll();
        Cart cart = new Cart(txt, list);
        List<Item> listItem = cart.getList();
        int n;
        if (listItem != null || listItem.isEmpty()) {
            n = listItem.size();
        } else {
            n = 0;
        }
        request.setAttribute("size", n);
        request.getRequestDispatcher("views/detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
