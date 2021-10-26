<!-- <%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->

<c:set var="USERID" value="${sessionScope.CURRENT_USER_USERID}" />
<c:set var="FIRSTNAME" value="${sessionScope.CURRENT_USER_FIRSTNAME}" />
<c:set var="LASTNAME" value="${sessionScope.CURRENT_USER_LASTNAME}" />
<c:set var="AVATAR" value="${sessionScope.CURRENT_USER_AVATAR}" />
<c:set var="EMAIL" value="${sessionScope.CURRENT_USER_EMAIL}" />
<c:set var="ROLE" value="${sessionScope.CURRENT_USER_ROLE}" />

<c:set var="USER_ROLE" value="0" />
<c:set var="ADMIN_ROLE" value="1" />
<c:set var="SUPER_ADMIN_ROLE" value="2" />

<nav class="nav">
    <div class="logo">
        <a class="logo__link" href="homeJSP">GGame</a>
    </div>
    <div tabindex="0" class="bars">
        <i class="bars__icon fa fa-bars" aria-hidden="true"></i>
    </div>
    <nav class="left">
        <div class="left__feature" id="home">
            <a class="left__link" href="homeJSP">Home</a>
        </div>
        <div class="left__feature" id="about">
            <a class="left__link" href="#">About</a>
        </div>
        <div class="left__feature" id="about">
            <a class="left__link" href="shop">Shop</a>
        </div>
        <div class="left__feature" id="shop">
            <a class="left__link" href="#">Apps</a>
        </div>
    </nav>

    <nav class="right">
        <div tabindex="0" class="right__welcome-user">
            <div class="right__icon-background">
                <i class="right__icon-user fa fa-user" aria-hidden="true"></i>
            </div>
        </div>

        <nav tabindex="0" class="right__subnav">
            <c:if test="${empty USERID}">
                <div class="right__not-login">
                    <a class="right__not-login__link" href="loginJSP">
                        <i class="right__not-login__icon fa fa-user"></i>
                        Login
                    </a>
                    <a class="right__not-login__link" href="signupJSP">
                        <i class="right__not-login__icon fa fa-user-plus" aria-hidden="true"></i>
                        Sign up
                    </a>
                </div>
            </c:if>
            <c:if test="${not empty USERID}">
                <div class="right__logged-in">
                    <div class="right__logged-in__user">
                        <div class="right__logged-in__user--information">
                            <img class="right__logged-in__avatar" width="100" height="100"
                                src="${pageContext.request.contextPath}${AVATAR}" alt="Avatar">
                            <p class="right__logged-in__name">${LASTNAME} ${FIRSTNAME}</p>
                            <div class="right__logged-in__email">${EMAIL}</div>
                        </div>

                        <div class="right__logged-in__user--actions">
                            <h3 class="right__logged-in__user--headline">Your account</h3>
                            <a class="right__logged-in__link right__logged-in__link--action" href="profile">
                                <i class="right__logged-in__icon fa fa-users" aria-hidden="true"></i>
                                Profile
                            </a>
                            <a class="right__logged-in__link right__logged-in__link--action" href="dashboard">
                                <i class="right__logged-in__icon fa fa-history" aria-hidden="true"></i>
                                Account History
                            </a>
                            <c:if test="${ROLE ge ADMIN_ROLE}">
                                <a class="right__logged-in__link right__logged-in__link--action" href="dashboard">
                                    <i class="right__logged-in__icon fa fa-tachometer" aria-hidden="true"></i>
                                    Dashboard
                                </a>
                            </c:if>
                            <a class="right__logged-in__link right__logged-in__link--action" href="dashboard">
                                <i class="right__logged-in__icon fa fa-wrench" aria-hidden="true"></i>
                                Settings
                            </a>
                        </div>
                    </div>
                    <div class="right__logged-in__bottom">
                        <a class="right__logged-in__link right__logged-in__link--switch" href="#">
                            Switch Account
                        </a>
                        <a class="right__logged-in__link right__logged-in__link--logout" href="logout">
                            Logout
                        </a>
                    </div>
                </div>
            </c:if>
        </nav>
    </nav>
</nav>