<%-- 
    Document   : profile
    Created on : Aug 20, 2021, 4:26:30 PM
    Author     : wifil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="USERID"     value="${sessionScope.CURRENT_USER_USERID}"/>
<c:set var="EMAIL"      value="${sessionScope.CURRENT_USER_EMAIL}"/>
<c:set var="LASTNAME"   value="${sessionScope.CURRENT_USER_LASTNAME}"/>
<c:set var="FIRSTNAME"  value="${sessionScope.CURRENT_USER_FIRSTNAME}"/>
<c:set var="BIRTHDAY"   value="${sessionScope.CURRENT_USER_BIRTHDAY}"/>
<c:set var="PHONE"      value="${sessionScope.CURRENT_USER_PHONE}"/>
<c:set var="AVATAR"     value="${sessionScope.CURRENT_USER_AVATAR}"/>

<c:set var="PASSWORD_KEY" value="1"/>
<c:set var="NAME_KEY" value="2"/>
<c:set var="BIRTHDAY_KEY" value="3"/>
<c:set var="PHONE_KEY" value="4"/>

<!DOCTYPE html>
<html>

<head>
    <title>Your profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navBar.css">
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/assets/js/navBar.js"></script>
    <!-- Font awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
    <section class="main">
        <!-- Header here  -->
        <header class="header">
            <!-- Navigation bar -->
            <jsp:include page="../general/navBar.jsp"/>
        </header>

        <section class="contents">
            <!-- Menu bar area -->
            <div class="menu">
                <article class="menu__area">
                    <h2 class="menu__headline">Your apps</h2>
                    <section class="menu__list">
                        <div class="menu__item">Tic tac toe</div>
                        <div class="menu__item">Puzzle</div>
                        <div class="menu__item">Football</div>
                        <div class="menu__item">Dark Soul</div>
                    </section>
                </article>
                <article class="menu__area">
                    <h2 class="menu__headline">Other services</h2>
                    <section class="menu__list">
                        <div class="menu__item">Premium II</div>
                        <div class="menu__item">EC2</div>
                        <div class="menu__item">GitHub</div>
                    </section>
                </article>
            </div>

            <!-- Profile area  -->
            <div class="profile">
                <img class="profile__avatar" 
                     src="${AVATAR}" alt="Your avatar">
                <h2 class="profile__headline">Profile</h2>
                <div class="profile__account-information">
                    <div class="profile__line profile__line--email">
                        <p class="profile__line__label">Email</p>
                        <p class="profile__line__value">${EMAIL}</p>
                    </div>

                    <div class="profile__line profile__line--password">
                        <c:if test="${requestScope.UPDATE_KEY ne PASSWORD_KEY}">
                            <p class="profile__line__label">Password</p>
                            <p class="profile__line__value">******</p>
                            <c:url var="URL_UPDATE_PASSWORD" value="profile">
                                <c:param name="btAction" value="update" />
                                <c:param name="key" value="${PASSWORD_KEY}" />
                            </c:url>
                            <a class="profile__line__link" href="${URL_UPDATE_PASSWORD}">Edit</a>
                        </c:if>

                        <c:if test="${requestScope.UPDATE_KEY eq PASSWORD_KEY}">
                            <c:url var="URL_UPDATE_PASSWORD_PANEL" value="updateProfile.jsp">
                                <c:param name="key" value="${PASSWORD_KEY}" />
                            </c:url>
                            <c:import url="${URL_UPDATE_PASSWORD_PANEL}" />
                        </c:if>
                    </div>

                    <div class="profile__line profile__line--fullname">
                        <c:if test="${requestScope.UPDATE_KEY ne NAME_KEY}">
                            <p class="profile__line__label">Your name</p>
                            <p class="profile__line__value">${LASTNAME} ${FIRSTNAME}</p>
                            <c:url var="UPDATE_NAME" value="profile">
                                <c:param name="btAction" value="update" />
                                <c:param name="key" value="${NAME_KEY}" />
                            </c:url>
                            <a class="profile__line__link" href="${UPDATE_NAME}">Edit</a>
                        </c:if>

                        <c:if test="${requestScope.UPDATE_KEY eq NAME_KEY}">
                            <c:url var="URL_UPDATE_NAME_PANEL" value="updateProfile.jsp">
                                <c:param name="key" value="${NAME_KEY}" />
                            </c:url>
                            <c:import url="${URL_UPDATE_NAME_PANEL}" />
                        </c:if>
                    </div>

                    <div class="profile__line profile__line--birthday">
                        <c:if test="${requestScope.UPDATE_KEY ne BIRTHDAY_KEY}">
                            <p class="profile__line__label">Birthday</p>
                            <p class="profile__line__value">${BIRTHDAY}</p>
                            <c:url var="UPDATE_BIRTHDAY" value="profile">
                                <c:param name="btAction" value="update" />
                                <c:param name="key" value="${BIRTHDAY_KEY}" />
                            </c:url>
                            <a class="profile__line__link" href="${UPDATE_BIRTHDAY}">Edit</a>
                        </c:if>

                        <c:if test="${requestScope.UPDATE_KEY eq BIRTHDAY_KEY}">
                            <c:url var="URL_UPDATE_BIRTHDAY_PANEL" value="updateProfile.jsp">
                                <c:param name="key" value="${BIRTHDAY_KEY}" />
                            </c:url>
                            <c:import url="${URL_UPDATE_BIRTHDAY_PANEL}" />
                        </c:if>
                    </div>

                    <div class="profile__line profile__line--phone">
                        <c:if test="${requestScope.UPDATE_KEY ne PHONE_KEY}">
                            <p class="profile__line__label">Phone number</p>
                            <p class="profile__line__value">${PHONE}</p>
                            <c:url var="UPDATE_PHONE" value="profile">
                                <c:param name="btAction" value="update" />
                                <c:param name="key" value="${PHONE_KEY}" />
                            </c:url>
                            <a class="profile__line__link" href="${UPDATE_PHONE}">Edit</a>
                        </c:if>

                        <c:if test="${requestScope.UPDATE_KEY eq PHONE_KEY}">
                            <c:url var="URL_UPDATE_PHONE_PANEL" value="updateProfile.jsp">
                                <c:param name="key" value="${PHONE_KEY}" />
                            </c:url>
                            <c:import url="${URL_UPDATE_PHONE_PANEL}" />
                        </c:if>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer here -->
        <footer class="footer">

        </footer>
    </section>
</body>

</html>