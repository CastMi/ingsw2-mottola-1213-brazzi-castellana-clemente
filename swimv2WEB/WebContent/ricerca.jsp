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
				<table>
					<tr>
						<td style="height: 20px; min-width: 60px">Username</td>
						<td style="height: 20px; min-width: 60px">Nome</td>
						<td style="height: 20px; min-width: 60px">Cognome</td>
						<td></td>
					</tr>
					<%
						for (int i = 0; i < risultato.length; i++) {
								if ((utenteLoggato == null || utenteLoggato.isEmpty() || !utenteLoggato
										.equals(risultato[i].getUsername()))) {
					%>
					<tr>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getUsername()%></td>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getNome()%></td>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getCognome()%></td>
						<td><form method="post" action="VaiAlProfiloDi">
								<input type="hidden" name="username"
									value="<%=risultato[i].getUsername()%>"> <input
									type="submit" value="Vai al profilo" />
							</form></td>
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