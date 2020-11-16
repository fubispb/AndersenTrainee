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
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
    </tr>
    <c:forEach items="${bucket}" var = "bucket">
        <tr>
            <td>${bucket.key.getId()}</td>
            <td>${bucket.key.getName()}</td>
            <td>${bucket.key.getPrice()}</td>
            <td>${bucket.value}
                <form action = "bucket" method="post">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input type="hidden" name="name" value="${product.getName()}">
                    <input type="hidden" name="age" value="${product.getPrice()}">
                    <input type="submit" value="Add" style="float:left">
                </form>
                <form action="deleteUser.jsp" method="post">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input type="submit" value="Delete" style="float:right">
                </form></td>
        </tr>
    </c:forEach>
</table>

<form action = "addUser.jsp">
    <input type="submit" value="Add product">
</form>
</body>
</html>