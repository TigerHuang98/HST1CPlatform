<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>Your Profile</h1>
Your user id is:<c:out value="${user.id}" /><br/>
First name:<c:out value="${user.firstName}" /><br/>
Last name:<c:out value="${user.lastName}" /><br/>
<a href=".">Back</a>
</body>
</html>
