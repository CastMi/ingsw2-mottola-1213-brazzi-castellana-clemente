<%@page import="it.swimv2.entities.remoteEntities.IAmicizia"%>
<%@page import="it.swimv2.entities.remoteEntities.IUtente"%>
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
	<jsp:param name="titolo" value="Lista amici" />
</jsp:include>

<div id="content">
	<div class="right">
		<div id="contentTitle">Lista Amici</div>
		<div class="articles">

			<%
				IAmicizia[] propriAmici = (IAmicizia[]) request
						.getAttribute("propriAmici");
				if (propriAmici != null && propriAmici.length > 0) {
			%>
			<table >
				<tr>
					<th>Username</th>
				</tr>
				<%
					int i = 0;
						String b;
						for (IAmicizia a : propriAmici) {
							i++;
							if (a.getIdUtente1().equals(utenteLoggato))
								b = a.getIdUtente2();
							else
								b = a.getIdUtente1();
				%>
				<tr <%if ((i % 2) == 0) {%> class="alt" <%}%>>
					<td><%=b%></td>
				</tr>
				<%
					}
				%>

			</table>
			<%
				} else {
			%>
			<div>
				<br />Non possiedi amici
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