<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Good List</title>
</head>
<body>
<h2>Menu:</h2>
<div><a href="myshop">Main page</a></div>
<div><a href="bucket">Your bucket</a></div>
<div>Enter count of goods you need1:</div>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
    </tr>
    <c:forEach items="${product}" var="product">
        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>
                <form action="goodlist" method="post">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input required type="number" name="count" value="0">
                    <input type="submit" value="Add" style="float:right">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>