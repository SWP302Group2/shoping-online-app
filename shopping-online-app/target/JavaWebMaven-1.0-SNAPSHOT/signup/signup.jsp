<%-- 
    Document   : signup
    Created on : Aug 19, 2021, 4:19:01 PM
    Author     : wifil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="ERRORS" value="${requestScope.ERRORS}"/>

<!DOCTYPE html>
<html>

    <head>
        <title>Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signup.css">
        <!-- Font awesome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body>
        <article class="box">
            <h1 class="box__headline">Sign Up</h1>
            <form class="box__form" action="signup" method="post">
                <div class="box__line">
                    <div class="box__control box__control--input">
                        <label for="email">Email</label>
                        <input class="box__input" id="email"
                                type="text" name="txtEmail" value="${param.txtEmail}">
                        <c:if test="${not empty ERRORS.emailFormatError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.emailFormatError}
                            </p>
                        </c:if>
                        <c:if test="${not empty ERRORS.emailAlreadyExist}">
                            <p class="box__message box__message--error">
                                ${ERRORS.emailAlreadyExist}
                            </p>
                        </c:if>
                    </div>
                    <p class="box__message">Your email address will not be published to third parties.</p>
                </div>

                <div class="box__line">
                    <div class="box__control box__control--input">
                        <label for="password">Password</label>
                        <input class="box__input" id="password"
                                type="password" name="txtPass" value="">
                        <c:if test="${not empty ERRORS.passwordFormatError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.passwordFormatError}
                            </p>
                        </c:if>
                    </div>
                    <div class="box__control box__control--input">
                        <label for="confirm">Confirm</label>
                        <input class="box__input" id="confirm"
                                type="password" name="txtConfirm" value="">
                        <c:if test="${not empty ERRORS.confirmNotMatch}">
                            <p class="box__message box__message--error">
                                ${ERRORS.confirmNotMatch}
                            </p>
                        </c:if>
                    </div>
                </div>

                <div class="box__line">
                    <div class="box__control box__control--input">
                        <label for="firstname">First name (Optional)</label>
                        <input class="box__input" id="firstname"
                                type="text" name="txtFirstname" value="${param.txtFirstname}">
                        <c:if test="${not empty ERRORS.firstnameLengthError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.firstnameLengthError}
                            </p>
                        </c:if>
                    </div>
                    <div class="box__control box__control--input">
                        <label for="lastname">Last name(Optional)</label>
                        <input class="box__input" id="lastname"
                                type="text" name="txtLastname" value="${param.txtLastname}">
                        <c:if test="${not empty ERRORS.lastnameLengthError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.lastnameLengthError}
                            </p>
                        </c:if>
                    </div>
                </div>
                
                <div class="box__line">
                    <div class="box__control box__control--input">
                        <label for="birthday">Birthday (Optional)</label>
                        <input class="box__input" id="birthday"
                                type="date" name="txtBirthday" value="${param.txtBirthday}">
                        <c:if test="${not empty ERRORS.birthdayFormatError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.birthdayFormatError}
                            </p>
                        </c:if>
                    </div>
                    <div class="box__control box__control--input">
                        <label for="phone">Phone Number</label>
                        <input class="box__input" id="phone"
                                type="number" name="txtPhone" value="${param.txtPhone}">
                        <c:if test="${not empty ERRORS.phoneFormatError}">
                            <p class="box__message box__message--error">
                                ${ERRORS.phoneFormatError}
                            </p>
                        </c:if>
                    </div>
                </div>

                <div class="box__line box__line--button">
                    <a class="box__link box__link--login" href="loginJSP">Login to existing account</a>
                    <div class="box__control box__control--button">
                        <input class="box__button" type="submit" name="btAction" value="Sign up">
                    </div>
                </div>
            </form>
            <div class="box__others">
                <a class="box__link" href="supportJSP">Need help?</a>
            </div>
        </article>
    </body>

</html>