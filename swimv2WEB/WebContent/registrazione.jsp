<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="registrazione" />
</jsp:include>

<div id="menu">
	<ul>
		<li><a href="index.jsp">Home</a></li>
		<li><a href="#">Ricerca domanda</a></li>
	</ul>
</div>

<div id="content">
	<div class="right">

		<h2>
			<a>REGISTRAZIONE</a>
		</h2>
		<div class="articles">
			<div class="main_content">
				<form action="RegistrazioneUtente" method="post">
					<fieldset>
						<table>
							<tr>
								<td class="td_campi_form"><label for="id">Nome:</label></td>
								<td style="height: 49px"><input name="nome" type="text"
									id="nome" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="id">Cognome:</label></td>
								<td style="height: 49px"><input name="cognome" type="text"
									id="cognome" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="id">Codice
										Persona:</label></td>
								<td style="height: 49px"><input name="codicepersona"
									type="text" id="codicepersona" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="id">Password:</label></td>
								<td style="height: 49px"><input name="password"
									type="password" id="password" class="auto-style3"
									style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="id">E-mail:</label></td>
								<td style="height: 49px"><input name="email" type="text"
									id="email" style="width: 185px" /></td>
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
						String message = (String) request.getAttribute("messaggio");

						if (message != null && !message.isEmpty()) {
					%>
					<tr>
						<td>
							<div id="erroreLogin">

								<%
									out.print(message);
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