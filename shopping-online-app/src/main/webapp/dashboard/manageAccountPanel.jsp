
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="UPDATE_ACCOUNT" value="${requestScope.UPDATE_ACCOUNT}"/>
<c:set var="ERRORS"         value="${requestScope.ERRORS}"/>
<c:set var="ROLE"           value="${sessionScope.CURRENT_USER_ROLE}"/>

<c:set var="USER_ROLE"        value="0"/>
<c:set var="ADMIN_ROLE"       value="1"/>
<c:set var="SUPER_ADMIN_ROLE" value="2"/>

<c:set var="INACTIVE" value="0"/>
<c:set var="ACTIVE"   value="1"/>
<c:set var="BANNED"   value="2"/>

<c:set var="PASSWORD_KEY" value="1"/>
<c:set var="NAME_KEY"     value="2"/>
<c:set var="BIRTHDAY_KEY" value="3"/>
<c:set var="PHONE_KEY"    value="4"/>
<c:set var="ROLE_KEY"     value="5"/>
<c:set var="STATUS_KEY"   value="6"/>

<section class="dashboard__update">
    <div class="dashboard__update__row">
        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Id</p>
            <p class="dashboard__update__value">${UPDATE_ACCOUNT.userId}</p>
        </div>
    </div>

    <div class="dashboard__update__row">
        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Email</p>
            <p class="dashboard__update__value">${UPDATE_ACCOUNT.email}</p>
        </div>
    </div>

    <div class="dashboard__update__row">
        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Status</p>
            <c:if test="${UPDATE_ACCOUNT.status eq INACTIVE}">
                <p class="dashboard__update__value">InActive</p>
            </c:if>
            <c:if test="${UPDATE_ACCOUNT.status eq ACTIVE}">
                <p class="dashboard__update__value">Active</p>
            </c:if>
            <c:if test="${UPDATE_ACCOUNT.status eq BANNED}">
                <p class="dashboard__update__value">Banned</p>
            </c:if>
        </div>
    </div> 

    <form class="dashboard__update__row" action="dashboard" method="POST">
        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="hidden" name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
        <input type="hidden" name="key" value="${PASSWORD_KEY}"/>

        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Password</p>
            <input class="dashboard__update__value" onkeyup="enableButton(event)"
                   type="password" name="txtPass" value="" placeholder="******">
            <button class="dashboard__update__button" 
                    type="submit" name="btAction" value="Update" disabled>Update</button>
        </div>

        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Confirm</p>
            <input class="dashboard__update__value" 
                   type="password" name="txtConfirm" value="" placeholder="******">
        </div>

        <div class="dashboard__update__line dashboard__update__line--message">
            <c:if test="${not empty ERRORS.passwordFormatError}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.passwordFormatError}
                </p>
            </c:if>
            <c:if test="${not empty ERRORS.confirmNotMatch}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.confirmNotMatch}
                </p>
            </c:if>
            <c:if test="${UPDATE_KEY eq PASSWORD_KEY}">
                <jsp:include page="../dashboard/updateMessage.jsp"/>
            </c:if>
        </div>
    </form>

    <form class="dashboard__update__row" action="dashboard" method="GET">
        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="hidden" name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
        <input type="hidden" name="key" value="${NAME_KEY}"/>

        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Name</p>
            <div class="dashboard__update__value">
                <input class="dashboard__update__name" onkeyup="enableButton(event)"
                       type="text" name="txtLastname" value="${UPDATE_ACCOUNT.lastname}" 
                       placeholder="Last name">
                <input class="dashboard__update__name" onkeyup="enableButton(event)"
                       type="text" name="txtFirstname" value="${UPDATE_ACCOUNT.firstname}" 
                       placeholder="First name">
            </div>
            <button class="dashboard__update__button" 
                    type="submit" name="btAction" value="Update" disabled>Update</button>
        </div>

        <div class="dashboard__update__line dashboard__update__line--message">
            <c:if test="${not empty ERRORS.firstnameLengthError}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.firstnameLengthError}
                </p>
            </c:if>
            <c:if test="${not empty ERRORS.lastnameLengthError}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.lastnameLengthError}
                </p>
            </c:if>
            <c:if test="${UPDATE_KEY eq NAME_KEY}">
                <jsp:include page="../dashboard/updateMessage.jsp"/>
            </c:if>
        </div>
    </form>

    <form class="dashboard__update__row" action="dashboard" method="GET">
        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="hidden" name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
        <input type="hidden" name="key" value="${BIRTHDAY_KEY}"/>

        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Birthday</p>
            <input class="dashboard__update__value" onkeyup="enableButton(event)"
                   type="date" name="txtBirthday" value="${UPDATE_ACCOUNT.birthday}">
            <button class="dashboard__update__button" 
                    type="submit" name="btAction" value="Update" disabled>Update</button>
        </div>

        <div class="dashboard__update__line dashboard__update__line--message">
            <c:if test="${not empty ERRORS.birthdayFormatError}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.birthdayFormatError}
                </p>
            </c:if>
            <c:if test="${UPDATE_KEY eq BIRTHDAY_KEY}">
                <jsp:include page="../dashboard/updateMessage.jsp"/>
            </c:if>
        </div>
    </form>

    <form class="dashboard__update__row" action="dashboard" method="GET">
        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="hidden" name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
        <input type="hidden" name="key" value="${PHONE_KEY}"/>

        <div class="dashboard__update__line">
            <p class="dashboard__update__label">Phone</p>
            <input class="dashboard__update__value" onkeyup="enableButton(event)"
                   type="text" name="txtPhone" value="${UPDATE_ACCOUNT.phone}">
            <button class="dashboard__update__button" 
                    type="submit" name="btAction" value="Update" disabled>Update</button>
        </div>

        <div class="dashboard__update__line dashboard__update__line--message">
            <c:if test="${not empty ERRORS.phoneFormatError}">
                <p class="dashboard__update__message dashboard__update__message--error">
                    ${ERRORS.phoneFormatError}
                </p>
            </c:if>
            <c:if test="${UPDATE_KEY eq PHONE_KEY}">
                <jsp:include page="../dashboard/updateMessage.jsp"/>
            </c:if>
        </div>
    </form>

    <c:if test="${ROLE gt ADMIN_ROLE}">
        <form class="dashboard__update__row" action="dashboard" method="GET">
            <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="hidden" name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
            <input type="hidden" name="key" value="${ROLE_KEY}"/>

            <div class="dashboard__update__line">
                <p class="dashboard__update__label">Role</p>
                <select class="dashboard__update__value" name="txtRole">
                    <c:if test="${UPDATE_ACCOUNT.role == USER_ROLE}">
                        <option value="${USER_ROLE}" selected>User</option>
                        <option value="${ADMIN_ROLE}">Manager</option>
                    </c:if>
                    <c:if test="${UPDATE_ACCOUNT.role == ADMIN_ROLE}">
                        <option value="${USER_ROLE}">User</option>
                        <option value="${ADMIN_ROLE}" selected>Manager</option>
                    </c:if>
                </select>
                <button class="dashboard__update__button" 
                        type="submit" name="btAction" value="Update">Update</button>
            </div>
            <div class="dashboard__update__line dashboard__update__line--message">
                <c:if test="${UPDATE_KEY eq ROLE_KEY}">
                    <jsp:include page="../dashboard/updateMessage.jsp"/>
                </c:if>
            </div>
        </form>
    </c:if>

    <div class="dashboard__update__buttons">
        <c:if test="${ROLE gt UPDATE_ACCOUNT.role}">
            <c:if test="${UPDATE_ACCOUNT.status ne BANNED}">
                <c:url var="URL_BAN_ACCOUNT" value="dashboard">
                    <c:param name="btAction" value="Change Status"/>
                    <c:param name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
                    <c:param name="txtStatus" value="${BANNED}"/>
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                </c:url>
                <a class="dashboard__update__delete" href="${URL_BAN_ACCOUNT}">
                    Ban account
                </a>
            </c:if>
            <c:if test="${UPDATE_ACCOUNT.status eq BANNED}">
                <c:url var="URL_UNBAN_ACCOUNT" value="dashboard">
                    <c:param name="btAction" value="Change Status"/>
                    <c:param name="txtUserId" value="${UPDATE_ACCOUNT.userId}"/>
                    <c:param name="txtStatus" value="${ACTIVE}"/>
                    <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                </c:url>
                <a class="dashboard__update__delete" href="${URL_UNBAN_ACCOUNT}">
                    Active
                </a>
            </c:if>
        </c:if>

        <c:url var="URL_CLOSE_PANEL" value="dashboard">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
        </c:url>
        <a class="dashboard__update__close" href="${URL_CLOSE_PANEL}">
            Close
        </a>
    </div>
</section>