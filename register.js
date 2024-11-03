<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kayıt Formu</title>
</head>
<body>
	<h2>Kayıt Formu</h2>
    <form:form method="post" action="${pageContext.request.contextPath}/save">    
        <table>
            <tr>
                <td><label for="firstName">Ad:</label></td>
                <td><form:input path="firstName" id="firstName" required="true"/></td>
            </tr>
            <tr>
                <td><label for="lastName">Soyad:</label></td>
                <td><form:input path="lastName" id="lastName" required="true"/></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><form:input path="email" id="email" type="email" required="true"/></td>
            </tr>
            <tr>
                <td><label for="password">Şifre:</label></td>
                <td><form:password path="password" id="password" required="true"/></td>
            </tr>
            <tr>
                <td> </td>
                <td><input type="submit" value="Kayıt Ol" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
