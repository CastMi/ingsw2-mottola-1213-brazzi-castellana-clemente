<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String leftCodeUtenteLoggato = (String) request.getSession()
			.getAttribute("nomeUtente");
	String leftCodeMessaggioLogin = (String) request
			.getAttribute("messaggioLogin");
%>

<div class="left">
	<%
		if (leftCodeUtenteLoggato == null
				|| leftCodeUtenteLoggato.isEmpty()) {
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
					if (leftCodeMessaggioLogin != null
								&& !leftCodeMessaggioLogin.isEmpty()) {
				%>
				<tr>
					<td>
						<div id="erroreLogin">
							<%=leftCodeMessaggioLogin%><br />
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
		} else {
	%>


	<div style="height: 65px;">
		<h2>
			Benvenuto<br>
			<%=leftCodeUtenteLoggato.toString()%>!
		</h2>
	</div>
	<form action="Logout" method="post">
		<input id="immagineLogout" name="submit" type="image"
			src="css/images/button-logout.jpg" alt="Logout" />
	</form>

	<%
		if (!leftCodeUtenteLoggato.equals("admin")) {
	%>

	<br /> <a href="homeUtente.jsp">Profilo</a> <br /> <a
		href="TutteLeAbilita">Le tue abilità</a> <br /> <a
		href="nuovaabilita.jsp">Aggiungi abilità</a> <br /> <a
		href="Suggerimenti">Suggerimenti</a> <br /> <a
		href="VisualizzaRichiesteAmicizia">Accetta Amicizia</a>
	<%
		} else {
	%>
	<br /> <a href="homeAmministratore.jsp">Home Amministratore</a>

	<%
		}

		}
	%>
</div>


