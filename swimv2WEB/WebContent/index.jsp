<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SwimV2</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
</head>
<body>

	<div id="wrap">

		<div id="header">
			<div id="title">
				<h1>
					<a href="index.jsp">SwimV2</a>
				</h1>
				<h2>Progetto di Ingegneria del software 2 - Brazzi Castellana
					Clemente</h2>
			</div>

			<div id="image">
				<img src="css/images/politecnico-milano.jpg" alt="Logo polimi" />
			</div>
		</div>

		<div id="menu">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="#">Ricerca domanda</a></li>
			</ul>
		</div>

		<div id="content">
			<div class="right">

				<h2>
					<a>Cos'è SwimV2?</a>
				</h2>
				<div class="articles">
					SWIMv2 è un software per lo scambio di informazioni di assistenza.<br />
					<br /> Questo obbiettivo è soddisfatto attraverso un network che
					permette di porre domande in determinati ambiti e consente agli
					utenti che possiedo queste abilità di fornire aiuto.<br /> <br />
					Nel sistema gli utenti possono diventare amici, in aggiunta vengono
					suggerite anche le possibili conoscenze in base alle proprie
					amicizie. <br /> <br /> Il tutto è supportato da un sistema di
					feedback per poter valutare l'affidabilità dell'utente che propone
					la soluzione al problema.
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
								<td><input type="text" name="userName" id="userName"
									size=19 /></td>
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
				<span id="registrati"> <a href="WEB-INF/registrazione.jsp"><br />Registrati!</a>
				</span>
			</div>
			<div style="clear: both;"></div>
		</div>
		
		<jsp:include page="Footer.jsp" />
		
	</div>

</body>
</html>
