<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IDomanda"%>
<%@page import="it.swimv2.entities.remoteEntities.IRisposta"%>
<%@page import="it.swimv2.entities.remoteEntities.IAbilita"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");
	IDomanda domanda = (IDomanda) request.getAttribute("domanda");

	IRisposta[] risposte = (IRisposta[]) request
			.getAttribute("arrayRisposte");

	if (domanda == null) {
		response.sendRedirect("index.jsp");
	}
	String messaggioRisposta = (String) request
			.getAttribute("messaggioRisposta");

	int idDomanda = domanda.getId();
	String nomeCreatore = domanda.getCreatore().getUsername();
	String titolo = domanda.getTitolo();
	String descrizioneDomanda = domanda.getDescrizione();
	//	domanda.getData();

	IAbilita[] abilita = domanda.getAbilita();

	String nomiAbilita;
	nomiAbilita = "";
	if (abilita != null && abilita.length > 0) {
		for (IAbilita a : abilita) {
			nomiAbilita += a.getNome() + " ";
		}
	}
	String titoloDomanda = domanda.getTitolo();
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="index" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Domanda:
			<%=titolo%></div>
		<br> <br>
		<h3>Descrizione:</h3>
		<br>
		<p><%=descrizioneDomanda%></p>
		<br> <br>
		<h3>Abilita:</h3>
		<br>
		<p><%=nomiAbilita%></p>
		<br> <br>
		<h3>Creatore:</h3>
		<br>
		<p><%=nomeCreatore%></p>
		<br> <br>
		<div class="articles">
			<%
				if (risposte != null && risposte.length > 0) {
			%>

			<table id="domrisp">
				<tr>
					<th>Risposta</th>
					<th>Punteggio</th>
					<th>Nome Utente</th>
					<%
						if (nomeCreatore.equals(utenteLoggato)) {
					%>
					<th>Feedback</th>
					<%
						}
					%>
				</tr>
				<%
					int i = 0;
						for (IRisposta r : risposte) {
							i++;
							int feedback = r.getFeedback();
				%>
				<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td><%=r.getDescrizione()%></td>
					<td><%=feedback%></td>
					<td><%=r.getUtente().getUsername()%></td>
					<%
						if (nomeCreatore.equals(utenteLoggato)) {
					%>
					<td>
						<form action="RilasciaFeedback" method="post">
							<fieldset>
								<input name="idRisposta" type="hidden" value="<%=r.getId()%>" />
								<select name="voto">
									<option value="0" <%=(feedback == 0) ? " selected" : ""%>>0</option>
									<option value="1" <%=(feedback == 1) ? " selected" : ""%>>1</option>
									<option value="2" <%=(feedback == 2) ? " selected" : ""%>>2</option>
									<option value="3" <%=(feedback == 3) ? " selected" : ""%>>3</option>
									<option value="4" <%=(feedback == 4) ? " selected" : ""%>>4</option>
									<option value="5" <%=(feedback == 5) ? " selected" : ""%>>5</option>
								</select> <input name="submit" type="submit" value="Assegna" />
							</fieldset>
						</form>
					</td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
			<%
				}
			%>


			<h2>Rispondi</h2>
			<form action="RispondiADomanda" method="post">
				<fieldset>
					<input name="idDomanda" type="hidden" value="<%=idDomanda%>" />
					<table>
						<tr>
							<td class="td_campi_form"><label for="descrizioneRisposta">Risposta:</label></td>
							<td style="height: 49px"><textarea
									name="descrizioneRisposta" rows="3"></textarea></td>
						</tr>
						<tr>
							<td class="td_campi_form"></td>
							<td align=center><input name="submit" type="submit"
								value="Rispondi" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<%
				if (messaggioRisposta != null && messaggioRisposta.length() > 0) {
			%>
			<div id="erroreRegistrazione">
				<%=messaggioRisposta%>
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
