
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="CATEGORIES" value="${applicationScope.CATEGORY_LIST}"/>

<article class="filter">
    <h2 class="filter__headline">Search Filters</h2>
    <form class="filter__form">
        <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}">

        <div class="filter__topic filter__topic--category">
            <h3 class="filter__topic__headline">Price range:</h3>

            <div class="filter__control filter__control--input">
                <label class="filter__label filter__label--input" for="min">Min price</label>
                <input class="filter__input" id="min" placeholder="Min"
                       type="text" name="txtMin" value="${param.txtMin}">
            </div>

            <div class="filter__control filter__control--input">
                <label class="filter__label filter__label--input" for="max">Max price</label>
                <input class="filter__input" id="max" placeholder="Max"
                       type="text" name="txtMax" value="${param.txtMax}">
            </div>
        </div>

        <div class="filter__topic filter__topic--category">
            <h3 class="filter__topic__headline">Category:</h3>

            <div class="filter__control filter__control--select">
                <select class="filter__select" name="txtCategoryId">
                    <option value="%" selected>All</option>
                    <c:if test="${not empty CATEGORIES}">
                        <c:forEach var="category" items="${CATEGORIES}">
                            <c:if test="${category.id eq param.txtCategoryId}">
                                <option value="${category.id}" selected>${category.name}</option>
                            </c:if>
                            <c:if test="${category.id ne param.txtCategoryId}">
                                <option value="${category.id}">${category.name}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>

        <div class="filter__topic filter__topic--sort"> 
            <h3 class="filter__control__headline">Order by:</h3>

            <div class="filter__control filter__control--check">
                <input class="filter__input filter__input--radio" id="order-price"
                       type="radio" name="txtOrder" value="1">
                <label class="filter__label filter__label--check" for="order-price">
                    Price</label>
            </div>

            <div class="filter__control filter__control--check">
                <input class="filter__input filter__input--radio" id="order-reserve-price"
                       type="radio" name="txtOrder" value="2">
                <label class="filter__label filter__label--check" for="order-reserve-price">
                    Reverse price</label>
            </div>

            <div class="filter__control filter__control--check">
                <input class="filter__input filter__input--radio" id="order-name"
                       type="radio" name="txtOrder" value="3">
                <label class="filter__label filter__label--check" for="order-name">
                    Name</label>
            </div>
        </div>

        <div class="filter__bottom">
            <button class="filter__button" type="submit" name="btAction" value="Search Product">
                Apply
            </button>
        </div>
    </form>
</article>