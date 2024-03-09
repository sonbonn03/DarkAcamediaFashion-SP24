<%-- 
    Document   : breadcrumbs
    Created on : Mar 10, 2024, 2:11:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Overview</li>
        <li class="breadcrumb-item ml-auto">
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal">
                Add Product
            </button>
        </li>
    </ol>
</html>
