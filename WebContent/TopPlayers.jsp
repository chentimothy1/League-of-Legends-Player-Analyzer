<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Top 10 Players</title>
<link rel="stylesheet" href="../style.css">
</head>
<body>
	<a href="LOLPlayerHP.jsp">
		<h1>${messages.title}</h1>
	</a>
	<div class="format">
		<table border="1">
            <tr>
                <th style="width:200px" >Summoner Name</th>
                <th style="width:100px" >Played Count</th>
            </tr>
            <c:forEach items="${topplayer}" var="topplayer">
                <tr>
                    <td><c:out value="${topplayer.getSummonerName()}" /></td>
                    <td class="number" ><c:out value="${topplayer.getCount()}" /></td>
                </tr>
            </c:forEach>
		</table>
    </div>
</body>
	<a href="../LOLPlayerHP.jsp">
		<footer>
			<div>League of Legend Player Analyzer </div>
		</footer>
	</a>
</html>