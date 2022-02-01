<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOL Player Analyzer</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<header>
		<h1>Welcome to League of Legend Player Analyzer</h1>
	</header>
		<div class="hpformat">
			<h3><u>Select for regional stats</u></h3>
			<div><a href="region?region=<c:out value="NA1"/>">North America</a></div>
			<div><a href="region?region=<c:out value="EUW1"/>">Europe West</a></div>
			<div><a href="region?region=<c:out value="EUN1"/>">Europe North</a></div>
		</div>
		<div class="hpformat">
			<h3><u>Search for specific summoner</u></h3>
			<div id="findSummoner"><a href="findsummoner">Find Summoner</a></div>
		</div>
		<div class="hpformat">
			<h3><u>Wiki pages</u></h3>
			<div id="champions"><a href="champions">Champions</a></div>
			<div id="items"><a href="items">Items</a></div>
			<div id="summonerSpells"><a href="summonerspells">Summoner Spells</a></div>
			<div id="runePage"><a href="runepage">Rune Page</a></div>
			<div id="maps"><a href="maps">Maps</a></div>
		</div>
		
	<footer>
		<div style="margin:5px"> League of Legend Player Analyzer </div>
		<div class="font">Created By:</div>
		<div class="font">Annie Pan</div>
		<div class="font">Timothy Chen</div>
		<div class="font">CS 5200 - DBMS</div>
		<div class="font">Summer 2020</div>
	</footer>
</body>
</html>