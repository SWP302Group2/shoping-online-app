<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="ROLE" value="${CURRENT_USER_ROLE}" />
<c:set var="ACCOUNT_LIST" value="${requestScope.SEARCH_RESULT}" />

<c:set var="USER_ROLE" value="0" />
<c:set var="ADMIN_ROLE" value="1" />
<c:set var="SUPER_ADMIN_ROLE" value="2" />

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
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
            <jsp:include page="../general/navBar.jsp" />
        </header>

        <section class="contents">
            <!-- Dashboard area  -->
            <div class="dashboard">
                <h1 class="dashboard__headline">Account management</h1>


                <form class="dashboard__search" action="dashboard" method="GET">
                    <input class="dashboard__search__input" placeholder="Search by name" type="text"
                        name="txtSearchValue" value="${param.txtSearchValue}">
                    <button class="dashboard__search__button" type="submit" name="btAction" value="Search">
                        <i class="dashboard__search__icon fa fa-search" aria-hidden="true"></i>
                    </button>
                </form>



                <c:if test="${not empty param.txtSearchValue}">
                    <c:if test="${empty ACCOUNT_LIST}">
                        <p class="dashboard__message">Your search does not match anything.</p>
                    </c:if>

                    <c:if test="${not empty ACCOUNT_LIST}">
                        <div class="dashboard__table dashboard__table--account">
                            <div class="dashboard__table__row dashboard__table__row--thead ">
                                <div class="dashboard__table__th">No.</div>
                                <div class="dashboard__table__th">Id</div>
                                <div class="dashboard__table__th">Email</div>
                                <div class="dashboard__table__th">Name</div>
                                <div class="dashboard__table__th">Birthday</div>
                                <div class="dashboard__table__th">Phone</div>
                                <div class="dashboard__table__th">Role</div>
                                <div class="dashboard__table__th">Manage</div>
                            </div>
                            <c:forEach var="account" items="${ACCOUNT_LIST}" varStatus="counter">
                                <div class="dashboard__table__row dashboard__table__row--th ">
                                    <div class="dashboard__table__td">${counter.count}</div>
                                    <div class="dashboard__table__td">${account.userId}</div>
                                    <div class="dashboard__table__td">${account.email}</div>
                                    <div class="dashboard__table__td">${account.lastname} ${account.firstname}</div>
                                    <div class="dashboard__table__td">${account.birthday}</div>
                                    <div class="dashboard__table__td">${account.phone}</div>
                                    <div class="dashboard__table__td">
                                        <c:if test="${ROLE eq ADMIN_ROLE or ROLE eq SUPER_ADMIN_ROLE}">
                                            <c:if test="${account.role eq SUPER_ADMIN_ROLE}">Administrator</c:if>
                                            <c:if test="${account.role eq ADMIN_ROLE}">Manager</c:if>
                                            <c:if test="${account.role eq USER_ROLE}">User</c:if>
                                        </c:if>
                                    </div>
                                    <div class="dashboard__table__td">
                                        <c:if test="${ROLE > account.role}">
                                            <c:url var="URL_UPDATE_ACCOUNT_PANEL" value="dashboard">
                                                <c:param name="btAction" value="Manage account" />
                                                <c:param name="txtUserId" value="${account.userId}" />
                                                <c:param name="txtSearchValue" value="${param.txtSearchValue}" />
                                            </c:url>
                                            <a class="dashboard__table__icon" href="${URL_UPDATE_ACCOUNT_PANEL}">
                                                <i class="fa fa-cog" aria-hidden="true"></i>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${not empty requestScope.UPDATE_ACCOUNT}">
                    <jsp:include page="../dashboard/manageAccountPanel.jsp" />
                </c:if>
            </div>
        </section>

        <!-- Footer here -->
        <footer class="footer">

        </footer>
    </section>
</body>

</html>