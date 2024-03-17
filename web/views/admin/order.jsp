<%-- 
    Document   : test
    Created on : Mar 17, 2024, 9:59:22 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Orders</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/vendor-admin/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="${pageContext.request.contextPath}/vendor-admin/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/colReorder-bootstrap4.css">
    <style>
        .error{
            color:red;
        }
    </style>


    <body>
    <body id="page-top">
        <jsp:include page="../common/admin/header-admin.jsp"></jsp:include>
            <div id="wrapper">
            <jsp:include page="../common/admin/sideBar.jsp"></jsp:include>
                <div id="content-wrapper">

                    <div class="container-fluid">
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fas fa-table"></i>
                                Data Table Manage Order
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th name="id">STT</th>
                                                <th width="10%">Name</th>
                                                <th>Image</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                                <th>Category</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${orders}" var="o" varStatus="loop">
                                            <tr>
                                                <td name="id">${loop.index + 1}</td>
                                                <td name="name">${o.fullName}</td>
                                                <td name="image">
                                                    ${o.phone}
                                                </td>
                                                <td name="quantity">${o.address}</td>
                                                <td>${o.getCreatedAt()}</td>
                                                <td name="category"> $<fmt:formatNumber value="${o.total}" type="number" pattern="#,##0.00" /></td>
                                                <td>
                                                    <a type="button" class="btn btn-outline-warning" href="./orderDetail?orderId=${o.id}">
                                                        Detail
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                    </div>
                </div> 
                <jsp:include page="../common/admin/footer-admin.jsp"></jsp:include>
                </div> 
            </div>
            <a class="scroll-to-top rounded" href="#">
                <i class="fas fa-angle-up"></i>
            </a>

        <script src="${pageContext.request.contextPath}/vendor-admin/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor-admin/bootstrap/js/bootstrap.bundle.min.js"></script>

        
        <script src="${pageContext.request.contextPath}/vendor-admin/jquery-easing/jquery.easing.min.js"></script>

        <!-- Page level plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor-admin/chart.js/Chart.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor-admin/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/vendor-admin/datatables/dataTables.bootstrap4.js"></script>

        <script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/colReorder-bootstrap4-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/colReorder-dataTables-min.js"></script>


        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/colReorder-dataTables-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/colReorder-bootstrap4-min.js"></script>


    </body>
</html>
