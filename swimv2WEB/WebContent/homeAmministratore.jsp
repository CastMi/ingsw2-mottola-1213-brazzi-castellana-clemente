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
			<table>
				<%
					IRichiestaAbilita[] richiesteAbilita = (IRichiestaAbilita[]) request
							.getAttribute("richiesteAbilita");
					if (richiesteAbilita != null && richiesteAbilita.length > 0) {
						for (IRichiestaAbilita a : richiesteAbilita) {
				%>
				<tr>
					<td><label for="username"> <%
 	a.getRichiedente();
 %>
					</label></td>
					<td>
						<form method="post" action="AccettaRichiesta">
							<input type="hidden" name="nomeRichiesta"
								value="<%a.getNome();%>"> <input type="hidden"
								name="username" value="<%a.getRichiedente();%>">
							<input name="submit" type="submit" value="Cancella" />
						</form>
					</td>
				</tr>
				<tr>
					<td><label for="nomeAbilita"> <%
 	a.getNome();
 %>
					</label></td>
					<td>
						<form method="post" action="RifiutaRichiesta">
							<input type="hidden" name="nomeRichiesta"
								value="<%a.getNome();%>"> <input type="hidden"
								name="username" value="<%a.getRichiedente();%>">
							<input name="submit" type="submit" value="Cancella" />
						</form>
					</td>
				</tr>
				<%
					}
					} else {
				%>
				<div><br/>Nessuna richiesta di abilità
				</div>
				<%
					}
				%>
			</table>
		</div>

	</div>

	<div class="left">

		<%
			utenteLoggato = (String) request.getSession().getAttribute(
					"nomeUtente");
		%>

		<span id="Benvenuto"><h2> Benvenuto <br /> <%
 	out.print(utenteLoggato);
 %>!</h2> <br /> <br /></span>
			<form action="Logout" method="post">
				<input id="immagineLogout" name="submit" type="image"
					src="css/images/button-logout.jpg" alt="Logout" />
			</form>

		
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />