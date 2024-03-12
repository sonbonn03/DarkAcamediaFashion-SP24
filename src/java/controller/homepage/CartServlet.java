
package controller.homepage;

import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Item;
import model.Product;

public class CartServlet extends HttpServlet {
   
   ProductDAO productDAO = new ProductDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("views/user/cart.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

   

}
