<%@page import="it.swimv2.entities.remoteEntities.IDomanda"%>
<%@page import="it.swimv2.entities.remoteEntities.IRisposta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Ricerca" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Ricerca testo all'interno di domande e risposte<br />
		</div>
		<div class="articles">
			<div class="main_content">
				<form action="RicercaDomandeRisposte" method="post">
					<fieldset>
						<table>
							<tr>
								<td class="td_campi_form"><label for="testo">Ricerca:</label></td>
								<td><input name="testo" type="text" /></td>
								<td><input type="submit" value="Ricerca" /></td>
							</tr>
						</table>
					</fieldset>
				</form>
				<%
					IDomanda[] domande = (IDomanda[]) request
							.getAttribute("arrayDomande");
					if (domande != null && domande.length > 0) {
				%>
				<h2>Domande trovate:</h2>
				<table id="domrisp">
					<tr>
						<th>Titolo</th>
						<th>Testo Domanda</th>
						<th></th>
					</tr>
					<%
						int i = 0;
							for (IDomanda d : domande) {
								i++;
					%>
					<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
						<td><%=d.getTitolo()%></td>
						<td><%=d.getDescrizione()%></td>
						<td><a href="ApriConversazione?id=<%=d.getId()%>">Apri</a></td>
					</tr>
					<%
						}
					%>
				</table>
				<%
					}
				%>

				<%
					IRisposta[] risposte = (IRisposta[]) request
							.getAttribute("arrayRisposte");
					if (risposte != null && risposte.length > 0) {
				%>
				<h2>Risposte trovate:</h2>
				<table id="domrisp">
					<tr>
						<th>Titolo Domanda</th>
						<th>Descrizione Risposta</th>
						<th></th>
					</tr>
					<%
						int c = 0;
							for (IRisposta r : risposte) {
								c++;
					%>
					<tr <%if ((c % 2) == 0) {%> class="alt" <%}%>>
						<td><%=r.getDomanda().getTitolo()%></td>
						<td><%=r.getDescrizione()%></td>
						<td><a
							href="ApriConversazione?id=<%=r.getDomanda().getId()%>">Apri</a></td>
					</tr>
					<%
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