<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Summoner Info</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<form method="post">
		<h1>Search for a Player by Summoner Name</h1>
		<div class = "format">
			<label for="summonername">SummonerName:</label> <input
				id="summonername" name="summonername"
				value="${fn:escapeXml(param.summonername)}">
		</div>
		<div class = "format">
			<INPUT TYPE="submit" VALUE="Find Summoner" onclick="form.action='findsummoner';"/> 
			<INPUT TYPE="submit" VALUE="Find Play Rate" onclick="form.action='findplayrate';"/>
			<INPUT TYPE="submit" VALUE="Find Matches" onclick="form.action='findmatches';"/>
			</br>
			<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</form>
	<div class = "format">
		<h2>Ranked Solo Game Statistics</h2>
		<table border="1">
			<tr>
				<th style="width:175px" >Total Matches</th>
				<th style="width:175px" >Win Rate</th>
			</tr>
			<summoner ="${summoner}" var="summoner">
			<tr>
				<td class="number"><c:out value="${summoner.getTotalMatches()}" /></td>
				<td class="number"><c:out value="${summoner.getWinrate()}" /></td>
			</tr>
		</table>
	</div>
	<div class = "format">
		<h2>Most Played Champions</h2>
		<table border="1">
			<tr>
				<th style="width:175px" >Champion Name</th>
				<th style="width:175px" >Number of Games Played</th>
			</tr>
			<c:forEach items="${championPlayRate}" var="c">
			<tr>
				<td><c:out value="${c.getChampionName()}" /></td>
				<td class="number"><c:out value="${c.getTimesPlayed()}" /></td>
			</tr>
			</c:forEach>
		
		</table>
	</div>
	<div class = "format">
		<h2>Match History</h2>
		<table border="1">
			<tr>
				<th style="width:175px" >MatchID</th>
				<th style="width:175px" >ChampionID</th>
				<th style="width:175px" >Played Role</th>
				<th style="width:175px" >PlayedPosition</th>
			</tr>
			<c:forEach items="${match}" var="m">
				<tr>
					<td class="number" ><c:out value="${m.getMatchID()}" /></td>
					<td class="number" ><c:out value="${m.getChampionID()}" /></td>
					<td><c:out value="${m.getPlayedRole()}" /></td>
		     		<td><c:out value="${m.getPlayedPosition()}" /></td>
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

