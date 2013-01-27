<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IUtente"%>

<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Dati Utente" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Home page Utente</div>
		<div class="articles">
			<table>
				<%
					IUtente utente = (IUtente) request.getAttribute("altroUtente");
				%>
				<!-- Username -->
				<tr>
					<td><b>Username:</b></td>
				</tr>
				<tr>
					<td><%=utente.getUsername()%></td>
				</tr>

				<!-- Nome -->
				<tr>
					<td><b>Nome:</b></td>
				</tr>
				<tr>
					<td><%=utente.getNome()%></td>
				</tr>

				<!-- Cognome -->
				<tr>
					<td><b>Cognome:</b></td>
				</tr>
				<tr>
					<td><%=utente.getCognome()%></td>
				</tr>

				<!-- Email -->
				<tr>
					<td><b>Email:</b></td>
				</tr>
				<tr>
					<td><%=utente.getEmail()%></td>
				</tr>
			</table>
			<form action="RichiestaAmicizia" method="post">
				<input type="hidden" name="destinatario"
					value="<%=utente.getUsername()%>">
				<textarea name="note" rows="3"> </textarea>
				<input value="Invia Richiesta Amicizia" type="submit" alt="Logout" />
			</form>
		</div>

	</div>

	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />