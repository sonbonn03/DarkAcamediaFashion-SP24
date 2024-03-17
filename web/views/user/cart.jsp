<%-- 
    Document   : cart
    Created on : Mar 5, 2024, 2:37:51 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Fruitables - Vegetable Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.5/dist/sweetalert2.min.css">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    </head>

    <body>

        <jsp:include page="../common/home/header.jsp"></jsp:include>


            <!-- Modal Search Start -->
            <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body d-flex align-items-center">
                            <div class="input-group w-75 mx-auto d-flex">
                                <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Search End -->


            <!-- Single Page Header start -->
            <div class="container-fluid page-header py-5">
                <h1 class="text-center text-white display-6">Cart</h1>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item active text-white">Cart</li>
                </ol>
            </div>
            <!-- Single Page Header End -->


            <!-- Cart Page Start -->
            <div class="container-fluid py-5">
                <div class="container py-5">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Handle</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cart.getList()}" var="c">
                                <tr>
                                    <th scope="row">
                                        <div class="d-flex align-items-center">
                                            <img src="${c.product.image}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                        </div>
                                    </th>
                                    <td>
                                        <p class="mb-0 mt-4">${c.product.name}</p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">$${c.product.price}</p>
                                    </td>
                                    <td>
                                        <div class="input-group quantity mt-4" style="width: 100px;">
                                            
                                            <!--minus-->
                                            <div class="input-group-btn">
                                                <button class="quantity-control quantity-left-minus btn btn-sm btn-minus rounded-circle bg-light border" data-type="minus" >
                                                    <i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                            <input type="text" max="${c.product.quantity}" id="quantity" name="quantity" product-id="${c.product.id}" class="form-control form-control-sm text-center border-0" value="${c.quantity}">
                                            <!--plus-->
                                            <div class="input-group-btn">
                                                <button class="quantity-control quantity-right-plus btn btn-sm btn-plus rounded-circle bg-light border" data-type="plus">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">  

                                            $<fmt:formatNumber value="${c.quantity * c.product.price}" type="number" pattern="#,##0.00" />
                                        </p>
                                    </td>
                                    <td>
                                        <button class="btn btn-md rounded-circle bg-light border mt-4" onclick="removeItem('${c.product.id}')">
                                            <i class="fa fa-times text-danger"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row g-4 justify-content-end">
                    <div class="col-8"></div>
                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                        <div class="bg-light rounded">
                            <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                <h5 class="mb-0 ps-4 me-4">Total</h5>
                                <p class="mb-0 pe-4">$<fmt:formatNumber value="${cart.getTotalMoney()}" type="number" pattern="#,##0.00" /></p>
                            </div>
                            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" id="checkout" type="button">Proceed Checkout</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cart Page End -->


        <!-- Footer -->
        <jsp:include page="../common/home/footer.jsp"></jsp:include>



            <!-- Back to Top -->
            <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


            <!-- JavaScript Libraries -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/waypoints/waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/lightbox/js/lightbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            function removeItem(productId){
                 window.location.href = "process?quantity=0" + "&id=" + productId;
            }
            
            //Plus & Minus for Quantity product
            $(document).ready(function () {
                var quantity = 1;
                $('.quantity-control').click(function (e) {
                    e.preventDefault();
                    var productId = $(this).closest('tr').find('[name="quantity"]').attr('product-id');
                    var quantityInput = $(this).closest('tr').find('[name="quantity"]');
                    var quantity = parseInt(quantityInput.val());
                    var action = $(this).data('type');

                    if (action === 'plus' && quantity < parseInt(quantityInput.attr('max'))) {
                       
                        quantityInput.val(quantity + 1);
                    } else if (action === 'minus' && quantity >= 1) {
                        quantityInput.val(quantity - 1);
                    }

                    window.location.href = "process?quantity=" + quantityInput.val() + "&id=" + productId;
                });

            });
            const quantity = document.querySelectorAll("input[name='quantity']");
            quantity.forEach((item) => {
                item.addEventListener("change", (e) => {
                    const max = parseInt(item.max);
                    if (e.target.value > max) {
                        e.target.value = max;
                        Swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: "You can just buy " + max,
                            timer: 2000
                        });
                    }
                    if (e.target.value <= 0) {
                        e.target.value = 1;
                        Swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: "You must be buy more than 0",
                            timer: 2000
                        });
                    }
                    const num = e.target.value;
                    const id = item.getAttribute("product-id");
                    window.location.href = "process?quantity=" + e.target.value + "&id=" + id;
                });
            });

            const checkout = document.getElementById("checkout");
            checkout.addEventListener("click", (e) => {
                 window.location.href = "checkout";
            });
        </script>
    </body>

</html>