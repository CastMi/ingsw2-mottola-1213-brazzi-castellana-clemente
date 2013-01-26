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
					IUtente io = (IUtente) request.getAttribute("utente");
				%>
				<!-- Username -->
				<tr>
					<td><b>Username: </b></td>
				</tr>
				<tr>
					<td><%=io.getUsername()%></td>
				</tr>

				<!-- Nome -->
				<tr>
					<td><b>Nome: </b></td>
				</tr>
				<tr>
					<td><%=io.getNome()%></td>
				</tr>

				<!-- Cognome -->
				<tr>
					<td><b>Cognome: </b></td>
				</tr>
				<tr>
					<td><%=io.getCognome()%></td>
				</tr>

				<!-- Email -->
				<tr>
					<td><b>Email:</b></td>
				</tr>
				<tr>
					<td><%=io.getEmail()%>
					</td>
				</tr>
			</table>
		</div>

	</div>

	<div class="left">

		<span id="Benvenuto"><h2>
				Benvenuto <br />
				<%
					out.print(utenteLoggato);
				%>!
				
			</h2> <br /> <br /></span>
			<form action="Logout" method="post">
				<input id="immagineLogout" name="submit" type="image"
					src="css/images/button-logout.jpg" alt="Logout" />
			</form>
			<p>
			<a href="TutteLeAbilita">Le tue abilità</a><p>
			<a href="TutteLeAbilita">Aggiungi abilità</a>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
