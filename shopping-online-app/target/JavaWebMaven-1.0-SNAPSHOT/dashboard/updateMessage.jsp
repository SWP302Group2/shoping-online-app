
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${not empty SUCCEED_MESSAGE}">
    <p class="dashboard__update__message dashboard__update__message--succeed">
        <i class="fa fa-check" aria-hidden="true"></i>
        Updated
    </p>
</c:if>
<c:if test="${not empty FAIL_MESSAGE}">
    <p class="dashboard__update__message dashboard__update__message--fail">
        <i class="fa fa-exclamation" aria-hidden="true"></i>
        Update failed
    </p>
</c:if>