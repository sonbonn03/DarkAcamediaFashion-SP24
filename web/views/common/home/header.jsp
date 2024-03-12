<%-- 
    Document   : header
    Created on : Mar 5, 2024, 3:51:10 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
</div>-->

<div class="container-fluid fixed-top">
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="index.html" class="navbar-brand"><h1 class="text-primary display-6">Fruitables</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a href="${pageContext.request.contextPath}/home" class="nav-item nav-link active">Home</a>
                    <c:if test="${sessionScope.account.roleId == 2}">
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-item nav-link active">Control</a>
                    </c:if>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <a href="cart.html" class="dropdown-item">Cart</a>
                            <a href="chackout.html" class="dropdown-item">Chackout</a>
                        </div>
                    </div>
                </div>
                <div class="d-flex m-3 me-0">
                    <form class="d-flex mt-4 mt-lg-0 ms-lg-auto ms-xl-0">
                        <div class="input-group-icon pe-2">
                            <form action="home" method="GET">
                                <input type="hidden" name ="search" value="searchProduct"/>
                                <input class="form-control border-0 input-box bg-100" type="text" placeholder="Search Food" aria-label="Search" name="keyword"/>
                                <a onclick="return this.closest('form').submit()"></a>
                            </form>
                        </div>
                    </form>


                    <a href="#" class="position-relative me-4 my-auto">
                        <i class="fa fa-shopping-bag fa-2x"></i>
                        <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                    </a>
                    <a class="my-auto">
                        <i class="fas fa-user fa-2x"></i>
                    </a>
                    <div class="nav-item dropdown">
                        <a href="#" class="my-auto" data-bs-toggle="dropdown">Account</a>
                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <c:if test="${sessionScope.account == null}">
                            <a href="login?action=login" class="dropdown-item">Login</a>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <a href="login?action=logout" class="dropdown-item">Logout</a>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>
        </nav>
    </div>
</div>
