<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOL Champions</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<a href="LOLPlayerHP.jsp">
		<h1>List of Champions</h1>
	</a>
	<div class="format">
		<table border="1">
        	<tr>
            	<th style="width:100px" >Name</th>
                <th style="width:150px" >Title</th>
                <th style="width:800px" >Blub</th>
                <th style="width:75px" >HP</th>
                <th style="width:75px" >Attack</th>
                <th style="width:75px" >Defense</th>
                <th style="width:75px" >Magic</th>
			</tr>
            <c:forEach items="${champions}" var="champions">
                <tr>
                    <td><a href="champions/topplayers?champion=<c:out value="${champions.getChampionName()}"/>"></ahref>
                    	<c:out value="${champions.getChampionName()}" /></td>
                    <td><c:out value="${champions.getChampionTitle()}" /></td>
                    <td><c:out value="${champions.getChampionBlub()}" /></td>
                    <td class="number" ><c:out value="${champions.getChampionHP()}" /></td>
                    <td class="number" ><c:out value="${champions.getChampionAttack()}" /></td>
                    <td class="number" ><c:out value="${champions.getChampionDefense()}" /></td>
                    <td class="number" ><c:out value="${champions.getChampionMagic()}" /></td>
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