<%-- 
    Document   : sideBar
    Created on : Mar 10, 2024, 2:12:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <ul class="sidebar navbar-nav">
      <li class="nav-item active">
          <a href="../admin/dashboard" class="nav-link" style="color: #34ce57">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span>
        </a>
      </li>
      <li class="nav-item active">
          <a href="./orders" class="nav-link" style="color: #34ce57">
          <i class="fas fa-fw fa-tachometer-alt"></i>    
          <span>Manager Orders</span>
        </a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/#" id="pagesDropdown" role="button" data-toggle="dropdown"
          aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-fw fa-folder" style="color: #34ce57"></i>
          <span style="color: #34ce57">Pages</span>
        </a>
          
        <div class="dropdown-menu" style="color: #34ce57" aria-labelledby="pagesDropdown">
            
          <h6 class="dropdown-header" style="color: #34ce57">Login Screens:</h6>
          <a class="dropdown-item" style="color: #34ce57" href="${pageContext.request.contextPath}/login?action=logout">Logout</a>
          <a class="dropdown-item" style="color: #34ce57" href="${pageContext.request.contextPath}/register.html">Register</a>
        </div>
      </li>
      
    </ul>
</html>
