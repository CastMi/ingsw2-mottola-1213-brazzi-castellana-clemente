<%@page import="it.swimv2.entities.remoteEntities.IUtente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");
%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Ricerca utenti" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Ricerca utenti<br />
		</div>
		<div class="articles">
			<div class="main_content">
				<form action="RicercaUtente" method="post">
					<fieldset>
						<table>
							<tr>
								<td class="td_campi_form"><label for="testo">Ricerca:</label></td>
								<td><input name="testoRicerca" type="text" /></td>
								<td><input type="submit" value="Ricerca" /></td>
							</tr>
						</table>
					</fieldset>
				</form>
				<%
					IUtente[] risultato = (IUtente[]) request
							.getAttribute("risultatoRicerca");
					if (risultato != null && risultato.length > 0) {
				%>
				<table id="domrisp">
					<tr>
						<th>Username</th>
						<th>Nome</th>
						<th>Cognome</th>
						<%
							if (utenteLoggato != null && !utenteLoggato.isEmpty()) {
						%>
						<th></th>
						<%
							}
						%>
					</tr>
					<%
						int c = 0;
							for (int i = 0; i < risultato.length; i++) {
								if ((utenteLoggato == null || utenteLoggato.isEmpty() || !utenteLoggato
										.equals(risultato[i].getUsername()))) {
									c++;
					%>
					<tr <%if ((c % 2) == 0) {%> class="alt" <%}%>>
						<td><%=risultato[i].getUsername()%></td>
						<td><%=risultato[i].getNome()%></td>
						<td><%=risultato[i].getCognome()%></td>
						<%
							if (utenteLoggato != null && !utenteLoggato.isEmpty()) {
						%>
						<td><form method="post" action="VaiAlProfiloDi">
								<input type="hidden" name="username"
									value="<%=risultato[i].getUsername()%>"> <input
									type="submit" value="Vai al profilo" />
							</form></td>
						<%
							}
						%>
					</tr>
					<%
						}
							}
					%>
				</table>
				<%
					}
				%>


			</div>

		</div>
	</div>

	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />