<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thanh Toán - Laptopshop</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet" />

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet" />
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
      .checkout-section {
          background-color: #f8f9fa;
          padding: 2rem;
          border-radius: 8px;
      }
      .order-summary {
          background-color: white;
          padding: 2rem;
          border-radius: 8px;
          box-shadow: 0 0 15px rgba(0,0,0,0.05);
      }
    </style>
  </head>
  <body>
    <!-- Spinner Start -->
    <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center">
      <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->
    
    <jsp:include page="../layout/header.jsp" />

    <!-- Checkout Page Start -->
    <div class="container-fluid py-5 mt-5">
      <div class="container py-5">
        <h1 class="mb-5">Billing details</h1>
        <div class="row g-5">
            <!-- Form Checkout -->
            <div class="col-md-12 col-lg-6 col-xl-7">
                <form action="/place-order" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="checkout-section">
                        <h4 class="mb-4">Thông Tin Người Nhận</h4>
                        <div class="mb-3">
                            <label class="form-label">Tên người nhận <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="receiverName" required placeholder="Họ và tên">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Địa chỉ người nhận <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="receiverAddress" required placeholder="Địa chỉ giao hàng">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="receiverPhone" required placeholder="Số điện thoại">
                        </div>
                        <div class="mt-4">
                            <button type="submit" class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary fw-bold" style="background-color: #fff;">Xác nhận đặt hàng</button>
                        </div>
                    </div>
                </form>
            </div>
            
            <!-- Thông tin đơn hàng -->
            <div class="col-md-12 col-lg-6 col-xl-5">
                <div class="order-summary">
                    <h4 class="mb-4">Thông Tin Thanh Toán</h4>
                    <div class="table-responsive mb-4">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Tên</th>
                                    <th scope="col">Giá cả</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cartDetail" items="${cartDetails}">
                                    <tr>
                                        <th scope="row">
                                            <div class="d-flex align-items-center mt-2">
                                                <img src="/images/image/${cartDetail.product.image}" class="img-fluid rounded-circle" style="width: 50px; height: 50px;" alt="">
                                            </div>
                                        </th>
                                        <td class="py-3">${cartDetail.product.name}</td>
                                        <td class="py-3">
                                            <fmt:formatNumber value="${cartDetail.product.price}" type="number" /> đ
                                        </td>
                                        <td class="py-3">${cartDetail.quantity}</td>
                                        <td class="py-3">
                                            <fmt:formatNumber value="${cartDetail.product.price * cartDetail.quantity}" type="number" /> đ
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                        <p class="mb-0 text-dark fw-bold">Tổng tiền sản phẩm</p>
                        <p class="mb-0 text-dark fw-bold"><fmt:formatNumber value="${totalPrice}" type="number" /> đ</p>
                    </div>
                    
                    <div class="d-flex justify-content-between mb-3 border-bottom pb-2">
                        <p class="mb-0 text-dark">Phí vận chuyển</p>
                        <p class="mb-0 text-dark">0 đ</p>
                    </div>
                    
                    <div class="d-flex justify-content-between mb-4 mt-3">
                        <h5 class="mb-0 text-dark">Tổng số tiền</h5>
                        <h5 class="mb-0 text-danger"><fmt:formatNumber value="${totalPrice}" type="number" /> đ</h5>
                    </div>
                    
                    <div class="d-flex justify-content-between mb-4 border-top pt-3">
                        <p class="mb-0 text-dark">Hình thức thanh toán</p>
                        <p class="mb-0 text-success fw-bold">Thanh toán khi nhận hàng (COD)</p>
                    </div>

                </div>
            </div>
        </div>
      </div>
    </div>
    <!-- Checkout Page End -->

    <jsp:include page="../layout/footer.jsp" />

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/client/js/main.js"></script>
  </body>
</html>
