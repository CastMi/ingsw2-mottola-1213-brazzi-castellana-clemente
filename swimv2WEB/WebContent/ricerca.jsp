<%@page import="it.swimv2.entities.remoteEntities.IUtente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Ricerca utenti" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Ricerca utenti<br />
		</div>
		<div class="articles">
			<div class="main_content">
				<form action="RicercaUtente" method="post">
					<fieldset>
						<table>
							<tr>
								<td class="td_campi_form"><label for="testo">Ricerca:</label></td>
								<td><input name="testoRicerca" type="text" /></td>
								<td><input type="submit" value="Ricerca" /></td>
							</tr>
						</table>
					</fieldset>
				</form>
				<%
					IUtente[] risultato = (IUtente[]) request
							.getAttribute("risultatoRicerca");
					if (risultato != null && risultato.length > 0) {
				%>
				<table>
					<tr>
						<td style="height: 20px; min-width: 60px">Username</td>
						<td style="height: 20px; min-width: 60px">Nome</td>
						<td style="height: 20px; min-width: 60px">Cognome</td>
						<td></td>
					</tr>
					<%
						for (int i = 0; i < risultato.length; i++) {
					%>
					<tr>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getUsername()%></td>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getNome()%></td>
						<td style="height: 20px; min-width: 60px"><%=risultato[i].getCognome()%></td>
						<td><form method="post" action="VaiAlProfiloDi">
								<input type="hidden" name="username"
									value="<%=risultato[i].getUsername()%>"> <input
									type="submit" value="Vai al profilo" />
							</form></td>
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
		<h2>
			<span id="Benvenuto"> Benvenuto <br /> <%
 	out.print(utenteLoggato);
 %>! <br /> <br /></span>
		</h2>
		<form action="Logout" method="post">
			<input id="immagineLogout" name="submit" type="image"
				src="css/images/button-logout.jpg" alt="Logout" />
		</form>
		<br /> <a href="homeUtente.jsp">Profilo</a> <br /> <a
			href="TutteLeAbilita">Le tue abilità</a> <br /> <a
			href="nuovaabilita.jsp">Aggiungi abilità</a>
		<%
			} else {
		%>
		<h2>Login</h2>
		<form action="Login" method="post">
			<fieldset>
				<table>
					<tr>
						<td><label for="userName">Username:</label></td>
					</tr>
					<tr>
						<td><input type="text" name="userName" id="userName" size=19 /></td>
					</tr>
					<tr>
						<td><label for="password">Password:</label></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="password"
							size=19 /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><input id="immagineLogin" name="submit" type="image"
							src="css/images/button-login.jpg" alt="Login" /></td>
					</tr>
					<%
						String messaggioLogin = (String) request
									.getAttribute("messaggioLogin");

							if (messaggioLogin != null && !messaggioLogin.isEmpty()) {
					%>
					<tr>
						<td>
							<div id="erroreLogin">

								<%
									out.print(messaggioLogin);
								%><br />
							</div>
						</td>
					</tr>
					<%
						}
					%>

				</table>

			</fieldset>
		</form>
		<span id="registrati"> <a href="FormRegistrazione"><br />Registrati!</a>
		</span>
		<%
			}
		%>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />