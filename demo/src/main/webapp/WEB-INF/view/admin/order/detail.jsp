<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Order Detail - SB Admin</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Dashboard</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/admin/order">Orders</a>
              </li>
              <li class="breadcrumb-item active">Order Detail</li>
            </ol>
            <div class="container mt-5">
              <div class="row">
                <div class="col-12 mx-auto">
                  <div class="d-flex justify-content-between align-items-center">
                    <h3>Order detail with id = ${id}</h3>
                    <div>
                      <a href="/admin/order/update/${id}" class="btn btn-warning me-2">Edit</a>
                      <a href="/admin/order" class="btn btn-secondary">Back</a>
                    </div>
                  </div>
                  <p class="text-muted mt-2">
                    Khách hàng: <strong>${order.user.fullname}</strong> &nbsp;|&nbsp;
                    Tổng tiền: <strong>${order.totalPrice} đ</strong> &nbsp;|&nbsp;
                    Trạng thái: <strong>${order.status}</strong>
                  </p>
                  <hr />
                  <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                      <tr>
                        <th>Sản phẩm</th>
                        <th>Tên</th>
                        <th>Giá cả</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="cartDetail" items="${orderDetails}">
                        <tr>
                          <td>
                            <img
                              src="/images/image/${cartDetail.product.image}"
                              style="max-width: 80px; border-radius: 50%;"
                            />
                          </td>
                          <td>
                            <a target="_blank" href="/product/${cartDetail.product.id}">
                              ${cartDetail.product.name}
                            </a>
                          </td>
                          <td>${cartDetail.price} đ</td>
                          <td>${cartDetail.quantity}</td>
                          <td>${cartDetail.price * cartDetail.quantity} đ</td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>
