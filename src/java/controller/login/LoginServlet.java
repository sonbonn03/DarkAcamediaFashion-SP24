/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import constant.CommonConst;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import model.Account;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");
        String url;
        switch (action) {
            case "login":
                url = "views/login.jsp";
                break;
            case "logout":
               logOut(request, response);
               return;
            default:
                url = "home";
        }

        //chuyen trang
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get ve action
        String action = request.getParameter("action") != null
                ? request.getParameter("action")
                : "";
        //dựa theo action để xử lí request
        String url;
        switch (action) {
            case "login":
                url = loginDoPost(request, response);
                return;
            case "signup":
                url = signUpDoPost(request, response);
                
                if(url == null){
                    response.sendRedirect("home");
                }else{
                    request.getRequestDispatcher(url).forward(request, response);
                }
                return;
                
            default:
                url = "views/home.jsp";
        }
        
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

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(CommonConst.SESSION_ACCOUNT);
        response.sendRedirect("home");
    }

    private String loginDoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = null;
        //get về các thong tin người dufg nhập
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //kiểm tra thông tin có tồn tại trong DB ko
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        Account accFound = accountDAO.findAccFound(account);
        if (accFound != null) {
            request.getSession().setAttribute(CommonConst.SESSION_ACCOUNT,
                    accFound);
            response.sendRedirect("home");
            return null;
        } else {
            request.setAttribute("errorLogin", "Username or password incorrect!!");
            url = "views/login.jsp";
        }
        return url;

    }

    private String signUpDoPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (accountDAO.checkUserExist(username) == true) {
            request.setAttribute("errorSignUp", "Username was existed!!");
            return url = "views/login.jsp";
        } else {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            accountDAO.createAccount(account);
            request.getSession().setAttribute(CommonConst.SESSION_ACCOUNT,
                    account); 
            
            return null;
        }

    }
}
