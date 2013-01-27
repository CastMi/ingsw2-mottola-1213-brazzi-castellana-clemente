<%@page import="it.swimv2.entities.RichiestaAmicizia"%>
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
	<jsp:param name="titolo" value="Richieste d'amicizia" />
</jsp:include>

<div id="content">
	<div class="right">
		<div id="contentTitle">Richieste d'amicizia:</div>
		<div class="articles">
			<%
				RichiestaAmicizia[] richiesteAmicizia = (RichiestaAmicizia[]) request
						.getAttribute("richiesteAmicizia");
				if (richiesteAmicizia == null || richiesteAmicizia.length == 0) {
					out.print("<br/>Non ci sono richieste d'amicizia!");
				} else {
			%>
			<table id="domrisp">
				<tr>
					<th>Username:</th>
					<th>Note</th>
					<th></th>
					<th></th>
				</tr>
				<%
				int i = 0;
					for (RichiestaAmicizia r : richiesteAmicizia) {
						i++;
				%>
				<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td><%=r.getIdRichiedente()%></td>
					<td><%=(r.getNote()==null)?"":r.getNote()%></td>
					<td>
						<form action="AccettaAmicizia" method="post">
							<input type="hidden" name="idRichiestaAmicizia"
								value="<%=r.getIdRichiestaAmicizia()%>"><input
								name="submit" type="submit" value="Accetta" />
						</form>
					</td>
					<td>
						<form action="RifiutaRichiestaAmicizia" method="post">
							<input type="hidden" name="idRichiestaAmicizia"
								value="<%=r.getIdRichiestaAmicizia()%>"><input
								name="submit" type="submit" value="Rifiuta" />
						</form>
					</td>
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
	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />