<%-- 
    Document   : detail
    Created on : Mar 5, 2024, 2:17:31 PM
    Author     : Admin
--%>

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


        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <style>
            .off{
                opacity:  0.7;
                pointer-events: none;
            }
            .mess-stock{
                display: none;
            }
            .mess-stock.on{
                display: block;
            }
        </style>
    </head>

    <body>

        <!--header-->
        <jsp:include page="common/home/header.jsp"></jsp:include>


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
                <h1 class="text-center text-white display-6">Product Detail</h1>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item active text-white">Product Detail</li>
                </ol>
            </div>
            <!-- Single Page Header End -->


            <!-- Single Product Start -->
            <div class="container-fluid py-5 mt-5">
                <div class="container py-5">
                    <div class="row g-4 mb-5">
                        <div class="col-lg-8 col-xl-9">
                            <div class="row g-4">
                                <div class="col-lg-6">
                                    <div>
                                        <a>
                                            <img src="${ProductFoundId.image}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="fw-bold mb-3">${ProductFoundId.name}</h4>
                                <p class="mb-3">Category: ${ProductFoundId.category.name}</p>
                                <h5 class="fw-bold mb-3">$${ProductFoundId.price} /kg</h5>
                                <p class="mb-4">${ProductFoundId.category.description}</p>
                                <form method="get" action="buy" id="formBuy">
                                    <input type="text" name="pid" value="${ProductFoundId.id}" hidden/>
                                    <div class="input-group quantity mb-5" style="width: 100px;">
                                        
                                        <!--minus-->
                                        <div class="input-group-btn">
                                            <button class="quantity-left-minus btn btn-sm btn-minus rounded-circle bg-light border" data-type="minus" >
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>
                                        
                                        <input type="text" class="form-control form-control-sm text-center border-0" id="quantity" name="quantity" min="1" max="${ProductFoundId.quantity}" value="1">
                                        
                                        <!--plus-->
                                        <div class="input-group-btn">
                                            <button class="quantity-right-plus btn btn-sm btn-plus rounded-circle bg-light border" data-type="plus">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                        
                                    <p class="mess-stock bg-danger text-center card text-white ${ProductFoundId.quantity == 0 ? 'on':''}">The product is out of stock</p>
                                    <button type="submit" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary">
                                        <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
                                    </button>
                                </form>
                            </div>
                            <div class="col-lg-12">
                                <nav>
                                    <div class="nav nav-tabs mb-3">
                                        <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                                id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                                aria-controls="nav-about" aria-selected="true">Description</button>

                                    </div>
                                </nav>
                                <div class="tab-content mb-5">
                                    <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                        <p>${ProductFoundId.category.description}</p>
                                        <p>${ProductFoundId.description}</p>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class=" text-center col-lg-4 col-xl-3" style="color: greenyellow">
                        <!--back to home-->
                        <a class="d-flex justify-content-center m-2 py-2 bg-light rounded-pill active" href="${pageContext.request.contextPath}/home">
                            <span style="color: #81c408">Back to home page</span>
                        </a>
                    </div>


                </div>

            </div>
        </div>
        <!-- Single Product End -->


        <!-- Footer Start -->
        <jsp:include page="common/home/footer.jsp"></jsp:include>   


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
        <script>
            //Plus & Minus for Quantity product
            $(document).ready(function () {
                var quantity = 1;
                const maxQuantity = document.getElementById("quantity").max;
                console.log(maxQuantity);
                $('.quantity-right-plus').click(function (e) {
                    e.preventDefault();
                    var quantity = parseInt($('#quantity').val());
                    if (quantity <= maxQuantity - 1) {
                        $('#quantity').val(quantity + 1);
                    }
                });

                $('.quantity-left-minus').click(function (e) {
                    e.preventDefault();
                    var quantity = parseInt($('#quantity').val());
                    if (quantity > 1) {
                        $('#quantity').val(quantity - 1);
                    }
                });

            });

            const formBuy = document.getElementById("formBuy");
            
            formBuy.addEventListener("submit", (e) => {
                e.preventDefault();
                const elementQuantity = document.getElementById("quantity");
                const messStock = document.querySelector(".mess-stock");
                if (parseInt(elementQuantity.value) < 0) {
                    messStock.innerHTML = "Quantity can't nagative number";
                    messStock.classList.add("on");
                    return;
                } else if (parseInt(elementQuantity.max) < parseInt(elementQuantity.value)) {
                    messStock.innerHTML = "The number of stock in the shop is not enough<br>Please decrease quantity";
                    messStock.classList.add("on");
                    return;
                } else {
                    formBuy.submit();
                }
                ;
            });
        </script>
    </body>

</html>
