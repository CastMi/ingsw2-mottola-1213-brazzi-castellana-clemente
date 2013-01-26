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
				<table>
					<tr>
						<td style="height: 49px">Titolo</td>
						<td style="height: 49px">Testo Domanda</td>
						<td style="height: 49px"></td>
					</tr>
					<%
						for (IDomanda d : domande) {
					%>
					<tr>
						<td style="height: 49px"><%=d.getTitolo()%></td>
						<td style="height: 49px"><%=d.getDescrizione()%></td>
						<td style="height: 49px"><a
							href="ApriConversazione?id=<%=d.getId()%>">Apri</a></td>
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
				<table>
					<tr>
						<td style="height: 49px">Titolo Domanda</td>
						<td style="height: 49px">Descrizione Risposta</td>
						<td style="height: 49px">&nsbp;</td>
					</tr>
					<%
						for (IRisposta r : risposte) {
					%>
					<tr>
						<td style="height: 49px"><%=r.getDomanda().getTitolo()%></td>
						<td style="height: 49px"><%=r.getDescrizione()%></td>
						<td style="height: 49px"><a
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

	<div class="left">
		<%
			String utenteLoggato = (String) request.getSession().getAttribute(
					"nomeUtente");
			if (utenteLoggato != null && !utenteLoggato.isEmpty()) {
		%>
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
		<%
			}
		%>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />