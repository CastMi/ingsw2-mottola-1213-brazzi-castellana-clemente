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
			<table>
				<tr>
					<td>Username:</td>
				</tr>
				<%
					for (RichiestaAmicizia r : richiesteAmicizia) {
				%>
				<tr>
					<td><%=r.getIdRichiedente()%> </td>
					<td> <%=r.getNote()%></td>
					<td>
						<form action="AccettaAmicizia" method="post">
							<input type="hidden" name="destinatario" value="<%=utenteLoggato%>">
							<input type="hidden" name="richiedente"
								value="<%=r.getIdRichiedente()%>"> <input name="submit"
								type="submit" value="Accetta" />
						</form>
					</td>
					<td>
						<form action="RifiutaRichiestaAmicizia" method="post">
							<input type="hidden" name="destinatario" value="<%=utenteLoggato%>">
							<input type="hidden" name="richiedente"
								value="<%=r.getIdRichiedente()%>"> 
								<input type="hidden" name="note" value="<%=r.getNote()%>"><input name="submit"
								type="submit" value="Rifiuta" />
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

	<div class="left">

		<%
			utenteLoggato = (String) request.getSession().getAttribute(
					"nomeUtente");
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


	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />