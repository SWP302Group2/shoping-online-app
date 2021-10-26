<%-- 
    Document   : updateProfile
    Created on : Aug 22, 2021, 12:17:02 PM
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
<c:set var="NAME_KEY"     value="2"/>
<c:set var="BIRTHDAY_KEY" value="3"/>
<c:set var="PHONE_KEY"    value="4"/>

<c:if test="${requestScope.UPDATE_KEY eq PASSWORD_KEY}">
    <article class="profile__update">
        <h2 class="profile__update__headline">Change password</h2>

        <form class="profile__update__form" action="profile" method="POST">
            <div class="profile__update__control profile__update__control--input">
                <label for="old-password">Old Password</label>
                <input class="profile__update__input" id="old-password"
                       type="password" name="txtOldPass" value="" >
                <c:if test="${not empty OLD_PASSWORD_INCORRECT}">
                    <p class="profile__update__message profile__update__message--error">
                        ${OLD_PASSWORD_INCORRECT}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--input">
                <label for="new-password">New password</label>
                <input class="profile__update__input" id="new-password"
                       type="password" name="txtNewPass" value="">
                <c:if test="${not empty ERRORS.passwordFormatError}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.passwordFormatError}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--input">
                <label for="confirm">Confirm</label>
                <input class="profile__update__input" id="confirm"
                       type="password" name="txtConfirm" value="" >
                <c:if test="${not empty ERRORS.confirmNotMatch}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.confirmNotMatch}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--button">
                <input type="hidden" name="key" value="${PASSWORD_KEY}">
                <input class="profile__update__button"
                       type="submit" name="btAction" value="Apply">
                <a class="profile__update__button profile__update__button--close"
                   href="profile">Close</a>
                <c:if test="${not empty SUCCEED_MESSAGE}">
                    <p class="profile__update__message profile__update__message--succeed">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Applied
                    </p>
                </c:if>
                <c:if test="${not empty FAIL_MESSAGE}">
                    <p class="profile__update__message profile__update__message--fail">
                        <i class="fa fa-exclamation" aria-hidden="true"></i>
                        Apply failed
                    </p>
                </c:if>
            </div>
        </form>
    </article>
</c:if>

<c:if test="${requestScope.UPDATE_KEY eq NAME_KEY}">
    <article class="profile__update">
        <h2 class="profile__update__headline">Change your name</h2>

        <form class="profile__update__form" action="profile" method="GET">
            <div class="profile__update__control profile__update__control--input">
                <label for="firstname">First name</label>
                <input class="profile__update__input" id="firstname"
                       type="text" name="txtFirstname" value="${FIRSTNAME}" >
                <c:if test="${not empty ERRORS.firstnameLengthError}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.firstnameLengthError}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--input">
                <label for="lastname">Last name</label>
                <input class="profile__update__input" id="lastname"
                       type="text" name="txtLastname" value="${LASTNAME}" >
                <c:if test="${not empty ERRORS.lastnameLengthError}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.lastnameLengthError}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--button">
                <input type="hidden" name="key" value="${NAME_KEY}">
                <input class="profile__update__button"
                       type="submit" name="btAction" value="Apply">
                <a class="profile__update__button profile__update__button--close"
                   href="profile">Close</a>
                <c:if test="${not empty SUCCEED_MESSAGE}">
                    <p class="profile__update__message profile__update__message--succeed">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Applied
                    </p>
                </c:if>
                <c:if test="${not empty FAIL_MESSAGE}">
                    <p class="profile__update__message profile__update__message--fail">
                        <i class="fa fa-exclamation" aria-hidden="true"></i>
                        Apply failed
                    </p>
                </c:if>
            </div>
        </form>
    </article>
</c:if>

<c:if test="${requestScope.UPDATE_KEY eq BIRTHDAY_KEY}">
    <article class="profile__update">
        <h2 class="profile__update__headline">Change your birthday</h2>

        <form class="profile__update__form" action="profile" method="GET">
            <div class="profile__update__control profile__update__control--input">
                <label for="birthday">Birthday</label>
                <input class="profile__update__input" id="birthday"
                       type="date" name="txtBirthday" value="${BIRTHDAY}" >
                <c:if test="${not empty ERRORS.birthdayFormatError}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.birthdayFormatError}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--button">
                <input type="hidden" name="key" value="${BIRTHDAY_KEY}">
                <input class="profile__update__button"
                       type="submit" name="btAction" value="Apply">
                <a class="profile__update__button profile__update__button--close"
                   href="profile">Close</a>
                <c:if test="${not empty SUCCEED_MESSAGE}">
                    <p class="profile__update__message profile__update__message--succeed">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Applied
                    </p>
                </c:if>
                <c:if test="${not empty FAIL_MESSAGE}">
                    <p class="profile__update__message profile__update__message--fail">
                        <i class="fa fa-exclamation" aria-hidden="true"></i>
                        Apply failed
                    </p>
                </c:if>
            </div>
        </form>
    </article>
</c:if>

<c:if test="${requestScope.UPDATE_KEY eq PHONE_KEY}">
    <article class="profile__update">
        <h2 class="profile__update__headline">Change your phone number</h2>

        <form class="profile__update__form" action="profile" method="GET">
            <div class="profile__update__control profile__update__control--input">
                <label for="phone">Phone Number</label>
                <input class="profile__update__input" id="phone"
                       type="number" name="txtPhone" value="${PHONE}" >
                <c:if test="${not empty ERRORS.phoneFormatError}">
                    <p class="profile__update__message profile__update__message--error">
                        ${ERRORS.phoneFormatError}
                    </p>
                </c:if>
            </div>

            <div class="profile__update__control profile__update__control--button">
                <input type="hidden" name="key" value="${PHONE_KEY}">
                <input class="profile__update__button"
                       type="submit" name="btAction" value="Apply">
                <a class="profile__update__button profile__update__button--close"
                   href="profile">Close</a>
                <c:if test="${not empty SUCCEED_MESSAGE}">
                    <p class="profile__update__message profile__update__message--succeed">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Applied
                    </p>
                </c:if>
                <c:if test="${not empty FAIL_MESSAGE}">
                    <p class="profile__update__message profile__update__message--fail">
                        <i class="fa fa-exclamation" aria-hidden="true"></i>
                        Apply failed
                    </p>
                </c:if>
            </div>
        </form>
    </article>
</c:if>
