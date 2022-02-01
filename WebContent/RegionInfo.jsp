<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Regional Information</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <a href="LOLPlayerHP.jsp">
		<h1>${messages.title}</h1>
	</a>
	<div class="format">
		<div id="findSummoner"><a href="findsummoner">Find Summoner</a></div>
	</div>
	<div class="format">
		<h3>Top 10 Highest Win Rate</h3>
		<table border="1">
            <tr>
                <th style="width:175px" >Summoner Name</th>
                <th style="width:100px" >Win Rate</th>
                <th style="width:75px" >Wins</th>
                <th style="width:75px" >Losses</th>
            </tr>
            <c:forEach items="${winrate}" var="winrate">
                <tr>
                    <td><c:out value="${winrate.getSummonerName()}" /></td>
                    <td  class="number" >
                    	<fmt:formatNumber var="winpercent" type="number" pattern="00.00" value="${winrate.getWinRate()*100}"></fmt:formatNumber>
                    	<c:out value="${winpercent}%"/></td>
                    <td class="number" ><c:out value="${winrate.getWins()}" /></td>
                    <td class="number" ><c:out value="${winrate.getLosses()}" /></td>
                </tr>
            </c:forEach>
		</table>
	</div>
	<div class="format">
       <h3>Top 10 Players by Role</h3>
		<table border="1"  class="table">
            <tr>
                <th>Solo</th>
                <th style="width:200px" >Duo</th>
                <th style="width:200px" >Duo Carry</th>
                <th style="width:200px" >Duo Support</th>
                <th style="width:200px" >None</th>
            </tr>
            <c:forEach items="${role}" var="role" >
                <tr>
                    <td><c:out value="${role[0]}"/></td>
                    <td><c:out value="${role[1]}"/></td>
                    <td><c:out value="${role[2]}"/></td>
                    <td><c:out value="${role[3]}"/></td>
                    <td><c:out value="${role[4]}"/></td>
                </tr> 
            </c:forEach>
		</table>
	</div>
	<div class="format">
       <h3>Top 10 Players by Position</h3>
		<table border="1" class="table">
            <tr>
                <th style="width:200px" >Top</th>
                <th style="width:200px" >Middle</th>
                <th style="width:200px" >Bottom</th>
                <th style="width:200px" >Jungle</th>
                <th style="width:200px" >None</th>
            </tr>
            <c:forEach items="${position}" var="position" >
                <tr>
                    <td><c:out value="${position[0]}"/></td>
                    <td><c:out value="${position[1]}"/></td>
                    <td><c:out value="${position[2]}"/></td>
                    <td><c:out value="${position[3]}"/></td>
                    <td><c:out value="${position[4]}"/></td>
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