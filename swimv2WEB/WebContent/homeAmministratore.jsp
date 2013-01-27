<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IRichiestaAbilita"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Home Amministratore" />
</jsp:include>

<div id="content">
	<div class="right">
		<div id="contentTitle">Home page Amministratore</div>
		<div class="articles">
			<br /> <a href="VisualizzaRichiesteAbilita">Vai alle richieste
				di abilità</a>
		</div>
	</div>
	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />