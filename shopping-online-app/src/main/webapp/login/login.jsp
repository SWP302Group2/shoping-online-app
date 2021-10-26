
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
    <!-- Font awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
    <section class="box">
        <div class="box__logo">
            <a class="box__logo__link" href="homeJSP">GGame</a>
        </div>
        <div class="box__content">
            <article class="box__login">
                <h2 class="box__headline">Login</h2>
                <form class="box__form" action="login" method="post">
                    <div class="box__control box__control--input">
                        <input class="box__input" id="email" autofocus=""
                               type="text" name="txtEmail" value="${param.txtEmail}" placeholder=" ">
                        <label class="box__label" for="email">Email</label>
                    </div>
                    <div class="box__control box__control--input">
                        <input class="box__input" id="password" 
                               type="password" name="txtPass" value="" placeholder=" ">
                        <label class="box__label" for="password">Password</label>
                    </div>
                    <div class="box__control box__control--button">
                        <input class="box__button" type="submit" name="btAction" value="Login">
                        <c:if test="${not empty requestScope.ERROR_MESSAGE}">
                            <p class="box__message box__message--error">${ERROR_MESSAGE}</p>
                        </c:if>
                        <div class="box__links">
                            <div class="box__signup">
                                Not yet signed up?
                                <a class="box__link" href="signupJSP">Create new account</a>
                            </div>
                            <div class="box__support">
                                <a class="box__link" href="supportJSP">Forget password?</a>
                            </div>
                        </div>
                    </div>
                </form>
            </article>

            <article class="box__others">
                <h2 class="box__others__headline">Sign-in with:</h2>
                <section class="box__others__options">

                </section>
            </article>
        </div>
    </section>

</body>

</html>