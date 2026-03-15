<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="vi">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Sản Phẩm - Laptopshop</title>
  <!-- Bootstrap CSS -->
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
  <style>
    body {
      font-family: 'Open Sans', sans-serif;
      background: #f8f9fa;
    }

    /* ---- Breadcrumb ---- */
    .breadcrumb-section {
      padding: 15px 0 10px;
    }
    .breadcrumb-section a {
      color: #28a745;
      text-decoration: none;
      font-weight: 600;
    }
    .breadcrumb-section span {
      color: #555;
    }

    /* ---- Sidebar ---- */
    .sidebar-card {
      background: #fff;
      border-radius: 12px;
      padding: 24px 20px;
      box-shadow: 0 2px 12px rgba(0,0,0,0.07);
      margin-bottom: 20px;
    }
    .sidebar-card h6 {
      font-weight: 700;
      color: #222;
      margin-bottom: 14px;
      font-size: 15px;
    }
    .filter-group label {
      display: flex;
      align-items: center;
      gap: 8px;
      font-size: 14px;
      color: #444;
      cursor: pointer;
      margin-bottom: 8px;
      transition: color .2s;
    }
    .filter-group label:hover {
      color: #28a745;
    }
    .filter-group input[type="checkbox"] {
      accent-color: #28a745;
      width: 16px;
      height: 16px;
    }

    .btn-apply-filter {
      background: #28a745;
      color: #fff;
      border: none;
      border-radius: 8px;
      width: 100%;
      padding: 10px;
      font-weight: 600;
      transition: background .2s;
      margin-top: 8px;
    }
    .btn-apply-filter:hover {
      background: #218838;
    }

    /* ---- Product Cards ---- */
    .product-grid .card {
      border: 1.5px solid #e5e5e5;
      border-radius: 14px;
      overflow: hidden;
      transition: box-shadow .25s, transform .25s;
      height: 100%;
    }
    .product-grid .card:hover {
      box-shadow: 0 8px 28px rgba(0,0,0,0.13);
      transform: translateY(-4px);
    }
    .product-grid .card-img-top {
      height: 200px;
      object-fit: cover;
      transition: transform .3s;
      background: #f5f5f5;
    }
    .product-grid .card:hover .card-img-top {
      transform: scale(1.04);
    }
    .product-badge {
      position: absolute;
      top: 12px;
      left: 12px;
      background: #ff9500;
      color: #fff;
      border-radius: 6px;
      padding: 3px 10px;
      font-size: 12px;
      font-weight: 600;
      z-index: 2;
    }
    .product-name a {
      color: #28a745;
      font-weight: 700;
      font-size: 15px;
      text-decoration: none;
    }
    .product-name a:hover {
      text-decoration: underline;
    }
    .product-short-desc {
      font-size: 13px;
      color: #777;
      margin-bottom: 10px;
      min-height: 36px;
    }
    .product-price {
      font-size: 17px;
      font-weight: 800;
      color: #222;
    }
    .btn-add-cart {
      border: 1.5px solid #28a745;
      color: #28a745;
      border-radius: 50px;
      padding: 7px 20px;
      font-size: 14px;
      font-weight: 600;
      background: transparent;
      transition: background .2s, color .2s;
      width: 100%;
    }
    .btn-add-cart:hover {
      background: #28a745;
      color: #fff;
    }
    .btn-add-cart i {
      margin-right: 6px;
    }

    /* ---- Sort Bar ---- */
    .sort-bar {
      background: #fff;
      border-radius: 10px;
      padding: 12px 20px;
      display: flex;
      align-items: center;
      gap: 16px;
      box-shadow: 0 1px 6px rgba(0,0,0,0.06);
      margin-bottom: 20px;
      flex-wrap: wrap;
    }
    .sort-bar label {
      font-size: 14px;
      font-weight: 600;
      color: #555;
      margin: 0;
    }
    .sort-bar select {
      border-radius: 8px;
      border: 1px solid #ddd;
      padding: 6px 12px;
      font-size: 14px;
    }
    .result-count {
      margin-left: auto;
      font-size: 14px;
      color: #888;
    }

    /* ---- Pagination ---- */
    .pagination-wrapper {
      display: flex;
      justify-content: center;
      margin-top: 36px;
    }
    .pagination .page-link {
      border-radius: 8px !important;
      margin: 0 3px;
      color: #28a745;
      border-color: #dee2e6;
      font-weight: 600;
      transition: all .2s;
    }
    .pagination .page-link:hover {
      background: #28a745;
      color: #fff;
      border-color: #28a745;
    }
    .pagination .page-item.active .page-link {
      background: #28a745;
      border-color: #28a745;
      color: #fff;
    }
    .pagination .page-item.disabled .page-link {
      color: #aaa;
    }

    /* ---- Empty State ---- */
    .empty-state {
      text-align: center;
      padding: 60px 20px;
      color: #999;
    }
    .empty-state i {
      font-size: 60px;
      margin-bottom: 20px;
      display: block;
      color: #ddd;
    }
  </style>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
  <!-- Spinner Start -->
  <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-grow text-primary" role="status"></div>
  </div>
  <!-- Spinner End -->

  <jsp:include page="../layout/header.jsp" />

  <!-- Page wrapper with top padding to clear fixed navbar -->
  <div class="container" style="margin-top: 90px;">

    <!-- Breadcrumb -->
    <div class="breadcrumb-section">
      <a href="/">Home</a>
      <span class="mx-2">/</span>
      <span>Danh Sách Sản Phẩm</span>
    </div>

    <div class="row g-4">
      <!-- ============ SIDEBAR ============ -->
      <div class="col-lg-3">

        <!-- Hãng sản xuất -->
        <div class="sidebar-card">
          <h6>Hãng sản xuất</h6>
          <form id="filter-form" action="/products" method="get">
            <div class="filter-group row">
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="Apple"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'Apple'}">checked</c:if></c:forEach></c:if> />
                  Apple
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="Asus"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'Asus'}">checked</c:if></c:forEach></c:if> />
                  Asus
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="Lenovo"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'Lenovo'}">checked</c:if></c:forEach></c:if> />
                  Lenovo
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="Dell"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'Dell'}">checked</c:if></c:forEach></c:if> />
                  Dell
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="LG"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'LG'}">checked</c:if></c:forEach></c:if> />
                  LG
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="factory" value="Acer"
                    <c:if test="${factories != null}"><c:forEach var="f" items="${factories}"><c:if test="${f == 'Acer'}">checked</c:if></c:forEach></c:if> />
                  Acer
                </label>
              </div>
            </div>

            <!-- Mục đích sử dụng -->
            <h6 class="mt-4">Mục đích sử dụng</h6>
            <div class="filter-group row">
              <div class="col-6">
                <label>
                  <input type="checkbox" name="target" value="Gaming"
                    <c:if test="${targets != null}"><c:forEach var="t" items="${targets}"><c:if test="${t == 'Gaming'}">checked</c:if></c:forEach></c:if> />
                  Gaming
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="target" value="Sinh vien - van phong"
                    <c:if test="${targets != null}"><c:forEach var="t" items="${targets}"><c:if test="${t == 'Sinh vien - van phong'}">checked</c:if></c:forEach></c:if> />
                  Sinh viên - văn phòng
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="target" value="Thiet ke do hoa"
                    <c:if test="${targets != null}"><c:forEach var="t" items="${targets}"><c:if test="${t == 'Thiet ke do hoa'}">checked</c:if></c:forEach></c:if> />
                  Thiết kế đồ họa
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="target" value="Mong nhe"
                    <c:if test="${targets != null}"><c:forEach var="t" items="${targets}"><c:if test="${t == 'Mong nhe'}">checked</c:if></c:forEach></c:if> />
                  Mỏng nhẹ
                </label>
              </div>
              <div class="col-12">
                <label>
                  <input type="checkbox" name="target" value="Doanh nhan"
                    <c:if test="${targets != null}"><c:forEach var="t" items="${targets}"><c:if test="${t == 'Doanh nhan'}">checked</c:if></c:forEach></c:if> />
                  Doanh nhân
                </label>
              </div>
            </div>

            <!-- Mức giá -->
            <h6 class="mt-4">Mức giá</h6>
            <div class="filter-group row">
              <div class="col-6">
                <label>
                  <input type="checkbox" name="price" value="duoi-10"
                    <c:if test="${priceRanges != null}"><c:forEach var="p" items="${priceRanges}"><c:if test="${p == 'duoi-10'}">checked</c:if></c:forEach></c:if> />
                  Dưới 10 triệu
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="price" value="10-15"
                    <c:if test="${priceRanges != null}"><c:forEach var="p" items="${priceRanges}"><c:if test="${p == '10-15'}">checked</c:if></c:forEach></c:if> />
                  Từ 10 - 15 triệu
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="price" value="15-20"
                    <c:if test="${priceRanges != null}"><c:forEach var="p" items="${priceRanges}"><c:if test="${p == '15-20'}">checked</c:if></c:forEach></c:if> />
                  Từ 15 - 20 triệu
                </label>
              </div>
              <div class="col-6">
                <label>
                  <input type="checkbox" name="price" value="tren-20"
                    <c:if test="${priceRanges != null}"><c:forEach var="p" items="${priceRanges}"><c:if test="${p == 'tren-20'}">checked</c:if></c:forEach></c:if> />
                  Trên 20 triệu
                </label>
              </div>
            </div>

            <!-- Sắp xếp -->
            <h6 class="mt-4">Sắp xếp</h6>
            <div class="filter-group">
              <label>
                <input type="radio" name="sort" value="price-asc"
                  <c:if test="${sort == 'price-asc'}">checked</c:if> />
                Giá tăng dần
              </label>
              <label>
                <input type="radio" name="sort" value="price-desc"
                  <c:if test="${sort == 'price-desc'}">checked</c:if> />
                Giá giảm dần
              </label>
            </div>

            <!-- Hidden fields for current page -->
            <input type="hidden" name="page" value="1" />

            <button type="submit" class="btn-apply-filter mt-3">
              <i class="fa fa-filter me-2"></i>Áp dụng bộ lọc
            </button>
          </form>
        </div>
      </div>
      <!-- ============ END SIDEBAR ============ -->

      <!-- ============ PRODUCT AREA ============ -->
      <div class="col-lg-9">
        <!-- Sort / Result bar -->
        <div class="sort-bar">
          <i class="fa fa-th-large text-muted"></i>
          <label>Danh sách sản phẩm</label>
          <span class="result-count">
            Hiển thị <strong>${currentPage * pageSize - pageSize + 1}</strong> -
            <strong>
              <c:choose>
                <c:when test="${currentPage * pageSize > totalItems}">${totalItems}</c:when>
                <c:otherwise>${currentPage * pageSize}</c:otherwise>
              </c:choose>
            </strong>
            trong tổng số <strong>${totalItems}</strong> sản phẩm
          </span>
        </div>

        <!-- Product grid -->
        <div class="product-grid">
          <c:choose>
            <c:when test="${empty products}">
              <div class="empty-state">
                <i class="fa fa-box-open"></i>
                <h5>Không tìm thấy sản phẩm nào</h5>
                <p>Thử thay đổi bộ lọc để tìm kiếm sản phẩm phù hợp.</p>
                <a href="/products" class="btn btn-outline-success mt-2">Xem tất cả sản phẩm</a>
              </div>
            </c:when>
            <c:otherwise>
              <div class="row g-4">
                <c:forEach var="product" items="${products}">
                  <div class="col-sm-6 col-xl-4">
                    <div class="card position-relative">
                      <!-- Badge -->
                      <span class="product-badge">${product.factory}</span>

                      <!-- Image -->
                      <a href="/product/${product.id}">
                        <img src="/images/image/${product.image}"
                             class="card-img-top"
                             alt="${product.name}"
                             onerror="this.src='/images/product/laptop-item-1.jpeg'" />
                      </a>

                      <div class="card-body d-flex flex-column">
                        <div class="product-name mb-1">
                          <a href="/product/${product.id}">${product.name}</a>
                        </div>
                        <p class="product-short-desc">${product.shortDesc}</p>
                        <div class="product-price mb-3">
                          <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" /> đ
                        </div>
                        <form action="/add-product-to-cart/${product.id}" method="post" class="mt-auto">
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                          <button type="submit" class="btn-add-cart">
                            <i class="fa fa-shopping-bag"></i>Add to cart
                          </button>
                        </form>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </c:otherwise>
          </c:choose>
        </div>

        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
          <div class="pagination-wrapper">
            <nav aria-label="Page navigation">
              <ul class="pagination">
                <!-- Previous -->
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                  <a class="page-link" href="/products?page=${currentPage - 1}&sort=${sort}&factory=${factoryParam}&target=${targetParam}&price=${priceParam}">
                    <i class="fa fa-chevron-left"></i>
                  </a>
                </li>

                <!-- Page numbers -->
                <c:forEach begin="1" end="${totalPages}" var="i">
                  <c:choose>
                    <c:when test="${totalPages > 7}">
                      <!-- Compact mode: show first, last, current ±1 -->
                      <c:if test="${i == 1 || i == totalPages || (i >= currentPage - 1 && i <= currentPage + 1)}">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                          <a class="page-link" href="/products?page=${i}&sort=${sort}&factory=${factoryParam}&target=${targetParam}&price=${priceParam}">${i}</a>
                        </li>
                      </c:if>
                      <c:if test="${(i == currentPage - 2 && currentPage > 3) || (i == currentPage + 2 && currentPage < totalPages - 2)}">
                        <li class="page-item disabled"><span class="page-link">…</span></li>
                      </c:if>
                    </c:when>
                    <c:otherwise>
                      <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="/products?page=${i}&sort=${sort}&factory=${factoryParam}&target=${targetParam}&price=${priceParam}">${i}</a>
                      </li>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <!-- Next -->
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                  <a class="page-link" href="/products?page=${currentPage + 1}&sort=${sort}&factory=${factoryParam}&target=${targetParam}&price=${priceParam}">
                    <i class="fa fa-chevron-right"></i>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </c:if>

      </div>
      <!-- ============ END PRODUCT AREA ============ -->
    </div>
  </div>

  <jsp:include page="../layout/footer.jsp" />

  <!-- Back to Top -->
  <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="/client/lib/easing/easing.min.js"></script>
  <script src="/client/lib/waypoints/waypoints.min.js"></script>
  <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
  <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="/client/js/main.js"></script>
</body>

</html>
