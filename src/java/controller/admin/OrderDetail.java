
package controller.admin;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Orders;

/*
 * @author HuynhPhan
 */
@WebServlet(name="OrderDetail", urlPatterns={"/admin/orderDetail"})
public class OrderDetail extends HttpServlet {
   
  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        OrderDAO orderDao = new OrderDAO();
        ArrayList<model.OrderDetail> listOrderDetail = orderDao.getOrderDetail(orderId);
        
        request.setAttribute("orderdetail", listOrderDetail);
        
        request.getRequestDispatcher("../views/admin/orderdetail.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

   

}
