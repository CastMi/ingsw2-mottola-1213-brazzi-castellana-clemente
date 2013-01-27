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
			<table id="domrisp">
				<tr>
					<th>Nome dell'abilità</th>
					<th>Descrizione</th>
				</tr>
				<%
				int i = 0;
					for (IAbilita a : proprieAbilita) {
						i++;
				%>
				<tr<%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td><h3><%=a.getNome()%></h3> 
					</td>
					<td rowspan="2">
						<form method="post" action="CancellaAbilita">
							<input type="hidden" name="nomeAbilita" value="<%=a.getNome()%>">
							<input name="submit" type="submit" value="Cancella" />
						</form>
					</td>
				</tr>
				<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td><%=a.getDescrizione()%></td>
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