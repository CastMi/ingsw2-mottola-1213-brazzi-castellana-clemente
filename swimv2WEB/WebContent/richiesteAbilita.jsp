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
	<jsp:param name="titolo" value="Richieste di abilità" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Home page Amministratore</div>
		<div class="articles">
			<%
				IRichiestaAbilita[] richiesteAbilita = (IRichiestaAbilita[]) request
						.getAttribute("richiesteAbilita");
				if (richiesteAbilita != null && richiesteAbilita.length > 0) {
			%>
			<table id="domrisp">
				<tr>
					<th>Richiedente</th>
					<th>Nome dell'abilità</th>
					<th>Accetta/Rifiuta</th>
				</tr>
				<%
				int i = 0;
					for (IRichiestaAbilita a : richiesteAbilita) {
						i++;
				%>
				<tr<%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td rowspan=2><%=a.getRichiedente()%></td>
					<td rowspan=2><%=a.getNome()%></td>
					<td>
						<form method="post" action="AccettaRichiestaAbilita">
							<input type="hidden" name="nomeRichiesta"
								value="<%=a.getNome()%>"> <input type="hidden"
								name="username" value="<%=a.getRichiedente()%>"> <input
								name="submit" type="submit" value="Accetta" />
						</form>
					</td>
				</tr>
				<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td>
						<form method="post" action="RifiutaRichiestaAbilita">
							<input type="hidden" name="nomeRichiesta"
								value="<%=a.getNome()%>"> <input type="hidden"
								name="username" value="<%=a.getRichiedente()%>"> <input
								name="submit" type="submit" value="Rifiuta" />
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				} else {
			%>
			<div>
				<br />Nessuna richiesta di abilità
			</div>
			<%
				}
			%>

		</div>

	</div>

	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />