<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Richiesta inviata" />
</jsp:include>

<div id="content">
	<div class="right">
		<div id="contentTitle">
			<%=request.getAttribute("messaggioAmicizia")%></div>
	</div>

	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />