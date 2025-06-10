<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Test Page</title>
</head>
<body>
    <h1>List of Universities <c:out value="${today}"/></h1>

    <c:choose>
        <c:when test="${not empty universities}">
            <c:forEach items="${universities}" var="university">
                <c:out value="${university.universityId}"/> :
                <c:out value="${university.universityName}"/><br/>
            </c:forEach>
        </c:when>
        <c:otherwise>
            No universities found.
        </c:otherwise>
    </c:choose>
</body>
</html>