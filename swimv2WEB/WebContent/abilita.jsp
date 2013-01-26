<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IAbilita"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="proprie abilità" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Proprie abilità</div>
		<div class="articles">
			<table>
				<%
					IAbilita[] proprieAbilita = (IAbilita[]) request
							.getAttribute("proprieAbilita");
					if (proprieAbilita != null) {
						for (IAbilita a : proprieAbilita) {
				%>
				<tr>
					<td><label for="userName"> <%
 	a.getNome();
 %>
					</label></td>
					<td rowspan="2">
						<form method="post" action="CancellaAbilita">
							<input type="hidden" name="nomeAbilita" value="<%a.getNome();%>">
							<input name="submit" type="submit" value="Cancella" />
						</form>
					</td>
				</tr>
				<tr>
					<td><label for="descrizione"> <%
 	a.getDescrizione();
 %>
					</label></td>
				</tr>
				<%
					}
					} else {
				%>
				<div>
					<br />Non possiedi alcuna abilità
				</div>
				<%
					}
				%>
			</table>
		</div>

	</div>

	<div class="left">

		<h2>
			Benvenuto
			<%
			String nomeStud = (String) session.getAttribute("nomeUtente");
			out.print(nomeStud);
		%>!
		</h2>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />