<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato != null && !utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="registrazione" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">REGISTRAZIONE</div>
		<br />
		<div class="articles">
			<div class="main_content">
				<form action="Registrazione" method="post">
					<fieldset>
						<table>
							<%
								String messaggioRegistrazione = (String) request
										.getAttribute("messaggioRegistrazione");

								if (messaggioRegistrazione != null
										&& !messaggioRegistrazione.isEmpty()) {
							%>
							<tr>
								<td>
									<div id="erroreRegistrazione">

										<%
											out.print(messaggioRegistrazione);
										%><br />
									</div>
								</td>
							</tr>
							<%
								}
							%>
							<tr>
								<td class="td_campi_form"><label for="nome">Nome:</label></td>
								<td style="height: 49px"><input name="nome" type="text"
									id="nome" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="cognome">Cognome:</label></td>
								<td style="height: 49px"><input name="cognome" type="text"
									id="cognome" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="nomeUtente">Codice
										Persona:</label></td>
								<td style="height: 49px"><input name="nomeUtente"
									type="text" id="nomeUtente" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="password">Password:</label></td>
								<td style="height: 49px"><input name="password"
									type="password" id="password" class="auto-style3"
									style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="email">E-mail:</label></td>
								<td style="height: 49px"><input name="email" type="text"
									id="email" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"></td>
								<td align=center><input id="immagineRegistrazione"
									name="submit" type="image"
									src="css/images/button-registrazione.jpg" alt="Registrazione" /></td>
							</tr>
						</table>
					</fieldset>
				</form>

			</div>

		</div>
	</div>
	<div class="left">

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
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
