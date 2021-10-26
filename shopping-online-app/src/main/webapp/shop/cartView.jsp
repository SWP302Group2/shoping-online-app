
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="PRODUCTS" value="${requestScope.CART_PRODUCT_LIST}"/>

<section class="cart">

    <div class="cart__content">
        <c:if test="${empty PRODUCTS}">
            <p class="cart__message cart__message--empty">Your cart is empty.</p>
        </c:if>

        <c:if test="${not empty PRODUCTS}">
            <c:set var="TOTAL_PRICE" value="${0}"/>
            <c:forEach var="product" items="${PRODUCTS}">
                <div class="cart__item">
                    <img class="cart__item__image" src="${product.image}" 
                         alt="${product.name}" height="100px" width="100px">
                    <div class="cart__item__content">
                        <h3 class="cart__item__title">
                            ${product.name} - ${product.description}
                        </h3>
                        <p class="cart__item__price">
                            ${product.price}đ / ${product.unit}
                        </p>
                        <form class="cart__item__form" action="shop" method="get">
                            <select class="cart__item__quantity" name="txtQuantity">
                                <c:forEach varStatus="counter" begin="0" end="9">
                                    <c:if test="${product.quantity ne counter.count}">
                                        <option value="${counter.count}">
                                            ${counter.count}
                                        </option>
                                    </c:if>
                                    <c:if test="${product.quantity eq counter.count}">
                                        <option value="${counter.count}" selected>
                                            ${counter.count}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="txtId" value="${product.id}">
                            <button class="cart__button cart__button--update" 
                                    name="btAction" value="Update Quantity">
                                Update
                            </button>
                            <c:url var="URL_REMOVE_ITEM" value="shop">
                                <c:param name="btAction" value="Remove"/>
                                <c:param name="txtId" value="${product.id}"/>
                            </c:url>
                            <a class="cart__button cart__button--remove" href="${URL_REMOVE_ITEM}">
                                Remove
                            </a>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <div class="last-row">
                <div class="td">
                    Subtotal (${sessionScope.NUMBER_PRODUCTS_IN_CART + 0} items): ${TOTAL_PRICE}đ
                </div>
            </div>
        </c:if>
    </div>

    <div class="cart__head">
        <h1 class="cart__headline">Your Cart</h1>
        <c:url var="URL_CHECK_OUT" value="shop">
            <c:param name="btAction" value="Process Checkout"/>
        </c:url>
        <c:if test="${not empty PRODUCTS}">
            <a class="cart__button cart__button--checkout" href="${URL_CHECK_OUT}">Process to checkout</a>
        </c:if>
    </div>

</section>