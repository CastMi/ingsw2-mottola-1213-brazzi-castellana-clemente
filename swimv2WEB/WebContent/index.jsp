<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="index" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Cos'è SwimV2?</div>
		<div class="articles">
			<br />SWIMv2 è un software per lo scambio di informazioni di
			assistenza.<br /> <br /> Questo obbiettivo è soddisfatto attraverso
			un network che permette di porre domande in determinati ambiti e
			consente agli utenti che possiedono queste abilità di fornire aiuto.<br />
			<br /> Nel sistema gli utenti possono diventare amici, in aggiunta
			vengono suggerite anche le possibili conoscenze in base alle proprie
			amicizie. <br /> <br /> Il tutto è supportato da un sistema di
			feedback per poter valutare l'affidabilità dell'utente che propone la
			soluzione al problema.
		</div>

	</div>

	<div class="left">
		<%
			String utenteLoggato = (String) request.getSession().getAttribute(
					"nomeUtente");

			if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		%>
		<h2>Login</h2>
		<form action="Login" method="post">
			<fieldset>
				<table>
					<tr>
						<td><label for="userName">Codice persona:</label></td>
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
		<span id="registrati"> <a href="registrazione.jsp"><br />Registrati!</a>
		</span>
		<%
			} else {
		%>

		<span id="Benvenuto"> <br />Benvenuto <br />
			<%
				out.print(utenteLoggato);
			%>!
			
		</span>
		<input id="immagineLogout" name="submit" type="image"
							src="css/images/button-logout.jpg" alt="Logout" />

		<%
			}
		%>

	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
