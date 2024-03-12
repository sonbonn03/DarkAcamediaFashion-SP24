/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.homepage;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class HomeServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> listProduct = findProductDoGet(request);

        List<Category> listCategory = categoryDAO.findAll();

        //set listProduct, listCategory vao session
        HttpSession session = request.getSession();
        session.setAttribute("listProduct", listProduct);
        session.setAttribute("listCategory", listCategory);
        String url = request.getRequestURI();
        session.setAttribute("prevPage", url);
        
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
        request.getRequestDispatcher("views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("home");
    }

    private List<Product> findProductDoGet(HttpServletRequest request) {
        //get ve search
        String actionSearch = request.getParameter("search") == null
                ? "default"
                : request.getParameter("search");

        List<Product> listProduct;

        switch (actionSearch) {
            case "category":
                // Inside your doGet method after you've determined the listCategory and listProduct
                String selectedCategoryId = request.getParameter("categoryId");
                request.setAttribute("selectedCategoryId", selectedCategoryId);
                listProduct = productDAO.findByCategory(selectedCategoryId);
                break;

            case "searchProduct":
                String keyword = request.getParameter("keyword");
                listProduct = productDAO.searchProductByName(keyword);
                break;

            default:
                listProduct = productDAO.findAll();
        }

        return listProduct;
    }

}
