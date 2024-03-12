
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
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Product;

/*
 * @author HuynhPhan
 */
public class BuyProductServlet extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String pId = request.getParameter("pid");
        String quantity = request.getParameter("quantity");
        ProductDAO productDao = new ProductDAO();
        List<Product> list = productDao.findAll();
        Cookie[] c = request.getCookies();
        String txt = "";
        if (c != null) {
            for (Cookie o : c) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                } 
            }
        }
        if (txt.isEmpty()) {
            txt += pId + ":" + quantity ;
        } else {
            txt += "/" + pId + ":" + quantity ;
        }
        Cookie cookie = new Cookie("cart", txt);
        cookie.setMaxAge(60 * 60 * 24 * 2);
        response.addCookie(cookie);
        Cart cart = new Cart(txt, list);
        List<Item> listItem = cart.getList();
        request.setAttribute("size", listItem.size());
        HttpSession session = request.getSession();
        String stayUrl = (String)session.getAttribute("stayPage");
        response.sendRedirect(stayUrl);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

   

}
