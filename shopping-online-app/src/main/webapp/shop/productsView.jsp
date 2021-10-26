
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="PRODUCTS" value="${requestScope.SEARCH_RESULT}"/>

<section class="product-box">
    <c:if test="${not empty PRODUCTS}">
        <c:forEach var="product" items="${PRODUCTS}">
            <div class="product-box__item">
                <img class="product-box__item__image" src="${product.image}" 
                     alt="${product.name}" height="100px" width="100px">
                <div class="product-box__item__content">
                    <h3 class="product-box__item__title">
                        ${product.name} - ${product.description}
                    </h3>
                    <p class="product-box__item__price">
                        ${product.price}đ / ${product.unit}
                    </p>
                    <c:url var="URL_ADD_TO_CART" value="shop">
                        <c:param name="btAction" value="Add To Cart"/>
                        <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                        <c:param name="txtCategoryId" value="${param.txtCategoryId}"/>
                        <c:param name="txtMin" value="${param.txtMin}"/>
                        <c:param name="txtMax" value="${param.txtMax}"/>
                        <c:param name="txtOrder" value="${param.txtOrder}"/>
                        <c:param name="txtId" value="${product.id}"/>
                    </c:url>
                    <a class="product-box__item__add-button" href="${URL_ADD_TO_CART}">
                        Add To Cart
                    </a>
                    <p class="product-box__item__quantity">
                        ${product.quantity} items available
                    </p>
                </div>
            </div>
        </c:forEach>
        
        <div class="product-box__item__bottom">
        </div>
    </c:if>
</section>