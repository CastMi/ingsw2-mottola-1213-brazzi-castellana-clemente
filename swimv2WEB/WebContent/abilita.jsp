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
	<jsp:param name="titolo" value="Proprie abilità" />
</jsp:include>

<div id="content">
	<div class="right">
		<div id="contentTitle">Proprie abilità</div>
		<div class="articles">

			<%
				IAbilita[] proprieAbilita = (IAbilita[]) request
						.getAttribute("proprieAbilita");
				if (proprieAbilita != null && proprieAbilita.length > 0) {
			%>
			<table>
				<tr>
					<td>Nome dell'abilità</td>
					<td>Descrizione</td>
				</tr>
				<%
					for (IAbilita a : proprieAbilita) {
				%>
				<tr>
					<td><label for="userName"> <%=a.getNome()%>
					</label></td>
					<td rowspan="2">
						<form method="post" action="CancellaAbilita">
							<input type="hidden" name="nomeAbilita" value="<%=a.getNome()%>">
							<input name="submit" type="submit" value="Cancella" />
						</form>
					</td>
				</tr>
				<tr>
					<td><label for="descrizione"> <%=a.getDescrizione()%>
					</label></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				} else {
			%>
			<div>
				<br />Non possiedi alcuna abilità
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