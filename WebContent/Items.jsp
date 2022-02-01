<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOL Items</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<a href="LOLPlayerHP.jsp">
		<h1>List of Items</h1>
	</a>
	<div class="format">
		<table border="1">
        	<tr>
            	<th style="width:200px" >Name</th>
                <th style="width:600px" >Description</th>
                <th style="width:400px" >Blurb</th>
                <th style="width:75px" >Price</th>
                <th style="width:75px" >Sell</th>
            </tr>
            <c:forEach items="${allitems}" var="allitems">
            	<tr>
                	<td><c:out value="${allitems.getItemName()}" /></td>
                    <td><c:out value="${allitems.getItemDesc()}" /></td>
                    <td><c:out value="${allitems.getItemBlurb()}" /></td>
                    <td class="number" ><c:out value="${allitems.getItemPrice()}" /></td>
                    <td class="number" ><c:out value="${allitems.getItemSell()}" /></td>
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