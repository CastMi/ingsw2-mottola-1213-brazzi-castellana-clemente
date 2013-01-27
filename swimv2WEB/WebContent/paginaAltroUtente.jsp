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
				<input type="hidden" name="username"
					value="<%=utente.getUsername()%>"> <input
					value="Invia Richiesta Amicizia" type="submit" alt="Logout" />
			</form>
		</div>

	</div>

	<div class="left">

		<h2>
			<span id="Benvenuto"> Benvenuto <br /> <%
 	out.print(utenteLoggato);
 %>! <br /> <br /></span>
		</h2>
		<form action="Logout" method="post">
			<input id="immagineLogout" name="submit" type="image"
				src="css/images/button-logout.jpg" alt="Logout" />
		</form>
		<br /> <a href="homeUtente.jsp">Profilo</a> <br /> <a
			href="TutteLeAbilita">Le tue abilità</a> <br /> <a
			href="nuovaabilita.jsp">Aggiungi abilità</a> <a href="Suggerimenti">Suggerimenti</a>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />