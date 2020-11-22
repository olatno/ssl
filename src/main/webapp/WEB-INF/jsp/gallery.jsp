<%--
  Created by IntelliJ IDEA.
  User: olatno
  Date: 18/11/2020
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="header.jsp" %>

<body>
<%@include file="nav.jsp" %>

<div class="container">

    <h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">Open Gallery</h1>

    <hr class="mt-2 mb-5">
    <c:forEach var="galleryMap" items="${imageGallery}">
        <h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">${galleryMap['title']} uploaded by ${galleryMap['author']}</h1>
        <c:forEach var="image" items="${galleryMap['images']}">
            <div class="row text-center text-lg-left">
                <div class="col-lg-3 col-md-4 col-6">
                    <a href="#" class="d-block mb-4 h-100">
                        <img class="img-fluid img-thumbnail" src="${image[0]}" alt="${image[1]}" style="width: 200px; height: 200px">
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:forEach>
</div>

    </footer>
</div>
</body>
</html>