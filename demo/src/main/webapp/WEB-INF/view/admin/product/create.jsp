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
    <title>Dashboard - SB Admin</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      $(document).ready(() => {
        const imageFile = $("#imageFile")
        imageFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0])
          $("#imagePreview").attr("src", imgURL)
          $("#imagePreview").css({ display: "block" })
        })
      })
    </script>
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
              <li class="breadcrumb-item active">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">Products</li>
            </ol>
            <div class="container mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Create a product</h3>
                  <hr />
                  <form:form
                    method="post"
                    action="/admin/product/create"
                    modelAttribute="newProduct"
                    enctype="multipart/form-data"
                  >
                    <div class="row">
                      <div class="col-6">
                        <label class="form-label">Name:</label>
                        <form:input
                          type="text"
                          class="form-control"
                          path="name"
                        />
                      </div>

                      <div class="col-6">
                        <label class="form-label">Price:</label>
                        <form:input
                          type="text"
                          class="form-control"
                          path="price"
                          value="0.0"
                        />
                      </div>

                      <div class="col-12 mb-3">
                        <label class="form-label">Detail descriptions:</label>
                        <form:textarea
                          path="detailDesc"
                          rows="4"
                          class="form-control w-100"
                        />
                      </div>

                      <div class="col-6">
                        <label class="form-label">Short description:</label>
                        <form:input
                          type="text"
                          class="form-control"
                          path="shortDesc"
                        />
                      </div>

                      <div class="col-6">
                        <label class="form-label">Quantity:</label>
                        <form:input
                          type="text"
                          class="form-control"
                          path="quantity"
                        />
                      </div>

                      <div class="col-6">
                        <label for="inputState" class="form-label"
                          >Factory:</label
                        >
                        <form:select
                          id="inputState"
                          class="form-select"
                          path="factory"
                        >
                          <form:option value="Apple"
                            >Apple(Macbook)</form:option
                          >
                          <form:option value="Microsoft">Microsoft</form:option>
                        </form:select>
                      </div>

                      <div class="col-6">
                        <label for="inputState" class="form-label"
                          >Target:</label
                        >
                        <form:select
                          id="inputState"
                          class="form-select"
                          path="target"
                        >
                          <form:option value="Gaming">Gaming</form:option>
                          <form:option value="Office">Office</form:option>
                        </form:select>
                      </div>

                      <div class="col-6">
                        <label for="imageFile" class="form-label">Image:</label>
                        <input
                          class="form-control"
                          type="file"
                          id="imageFile"
                          accept=".png,.jpg,.jpeg"
                          name="imageFile"
                        />
                      </div>
                      <div class="col-12 mb-3 col-md-6">
                        <img
                          style="max-height: 250px; display: none"
                          alt="image preview"
                          id="imagePreview"
                        />
                      </div>

                      <div class="col-12 mt-3">
                        <button type="submit" class="btn btn-primary">
                          Submit
                        </button>
                      </div>
                    </div>
                  </form:form>
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
