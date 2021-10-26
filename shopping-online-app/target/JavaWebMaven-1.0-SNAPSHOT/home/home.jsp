
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navBar.css">
    <!-- Favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png" type="image/x-icon">
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/assets/js/navBar.js"></script>
    <!-- Font awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
    <section class="main">
        <header class="header">
            <!-- Navigation bar -->
            <jsp:include page="../general/navBar.jsp"/>

        </header>

        <!-- Contents here -->
        <section class="contents">
            <h1 class="contents__headline">Welcome to GGame</h1>
        </section>

        <!-- Footer -->
        <footer class="footer">
            <article class="footer__social-medias">

            </article>

            <article class="footer__links">

            </article>

            <article class="footer__logo">

            </article>
        </footer>
    </section>
</body>

</html>