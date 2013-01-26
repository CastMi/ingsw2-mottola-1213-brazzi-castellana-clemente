<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IUtente"%>

<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");
%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Home page Utente" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Home page Utente</div>
		<div class="articles">
			<table>
				<%
					IUtente io = (IUtente) request.getAttribute("utente");
				%>
				<!-- Username -->
				<tr>
					<td><label>Username: </label></td>
				</tr>
				<tr>
					<td><label> <%
 	io.getUsername();
 %>
					</label></td>
				</tr>

				<!-- Nome -->
				<tr>
					<td><label>Nome: </label></td>
				</tr>
				<tr>
					<td><label> <%
 	io.getNome();
 %>
					</label></td>
				</tr>

				<!-- Cognome -->
				<tr>
					<td><label>Cognome: </label></td>
				</tr>
				<tr>
					<td><label> <%
 	io.getCognome();
 %>
					</label></td>
				</tr>

				<!-- Email -->
				<tr>
					<td><label>Email: </label></td>
				</tr>
				<tr>
					<td><label> <%
 	io.getEmail();
 %>
					</label></td>
				</tr>
			</table>
		</div>

	</div>

	<div class="left">

		<span id="Benvenuto"><h2>
				Benvenuto <br />
				<%
					out.print(utenteLoggato);
				%>!
			</h2>
			<br /> <br />
			<form action="Logout" method="post">
				<input id="immagineLogout" name="submit" type="image"
					src="css/images/button-logout.jpg" alt="Logout" />
			</form> </span>

	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
