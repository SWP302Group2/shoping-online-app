
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="NUMBER_ITEMS_IN_CART" value="${sessionScope.CART_TOTAL_QUANTITY}"/>


<section class="search-bar">
    <div class="search-bar__previous">
        <i class="search-bar__left-icon fa fa-arrow-left"></i>
    </div>
    
    <div class="search-bar__logo">
        <a class="search-bar__logo__link" href="shop">GShop</a>
    </div>
    
    <form class="search-bar__form" action="shop" method="GET">
        <input type="hidden" name="txtOrder" value="${param.txtOrder}">
        <input type="hidden" name="txtCategoryId" value="${param.txtCategoryId}">
        <input type="hidden" name="txtMin" value="${param.txtMin}">
        <input type="hidden" name="txtMax" value="${param.txtMax}">
        
        <input class="search-bar__input" type="text" name="txtSearchValue" 
               value="${param.txtSearchValue}" placeholder="Search products..">
        <button class="search-bar__button" type="submit" name="btAction" value="Search Product">
            <i class="search-bar__search-icon fa fa-search"></i>
        </button>
    </form>

    <c:url var="URL_CART_PAGE" value="shop">
        <c:param name="btAction" value="View Cart"/>
    </c:url>
    <a class="search-bar__cart" href="${URL_CART_PAGE}">
        <i class="search-bar__cart-icon"></i>
        <p class="search-bar__cart-quantity">${NUMBER_ITEMS_IN_CART}</p>
    </a>
</section>