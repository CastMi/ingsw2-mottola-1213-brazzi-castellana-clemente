<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Suggerimenti" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Suggerimenti per le richieste d'amicizia:</div>
		<div class="articles">



			<%
				String[] suggerimenti = (String[]) request
						.getAttribute("suggerimenti");
				if (suggerimenti == null || suggerimenti.length == 0) {
					out.print("<br/>Non ci sono suggerimenti!");
				} else {
			%>
			<table>
				<tr>
					<td>Username:</td>
				</tr>
				<%
					for (String s : suggerimenti) {
				%>
				<tr>
					<td><%=s%></td>
					<td>
						<form action="RichiestaAmiciziaDaSuggerimento" method="post">
							<input type="hidden" name="destinatario" value="<%=s%>">
							<input type="hidden" name="richiedente"
								value="<%=utenteLoggato%>"> <input name="submit"
								type="submit" value="Accetta" />
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