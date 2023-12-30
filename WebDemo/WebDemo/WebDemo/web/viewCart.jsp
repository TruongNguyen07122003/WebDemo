<%-- 
    Document   : viewCard
    Created on : Oct 31, 2023, 9:33:10 PM
    Author     : GIGABYTE
--%>

<%@page import="java.util.Map"%>
<%@page import="truongnt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1" cellspacing="2">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${items}" var="entry" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${entry.key}</td>
                                    <td>${entry.value}</td>
                                    <td>
                                        <input type="checkbox" name="checkItem" value="${entry.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="bookStore.jsp">Add more books to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Check-out" name="btAction" />
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>No cart is existed</h2>
            <a href="bookStore.jsp">Click here to return BookStore</a>
        </c:if>
    </body>
</html>
