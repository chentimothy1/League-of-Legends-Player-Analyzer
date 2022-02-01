<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOL Rune Pages</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<a href="LOLPlayerHP.jsp">
		<h1>List of Rune Pages</h1>
	</a>
	<div class="format">
		<table border="1">
            <tr>
                <th style="width:125px" >Name</th>
                <th style="width:125px" >Path</th>
                <th style="width:600px" >Description</th>
            </tr>
            <c:forEach items="${runepages}" var="runepages">
                <tr>
                    <td><c:out value="${runepages.getRunePageName()}" /></td>
                    <td><c:out value="${runepages.getRunePath()}" /></td>
                    <td><c:out value="${runepages.getRunePageDesc()}" /></td>
                </tr>
            </c:forEach>
		</table>
		</br>
	</div>
	<a href="LOLPlayerHP.jsp">
		<footer>
			<div>League of Legend Player Analyzer </div>
		</footer>
	</a>
</body>
</html>