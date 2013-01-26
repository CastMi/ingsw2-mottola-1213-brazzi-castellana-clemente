<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.swimv2.entities.remoteEntities.IDomanda"%>
<%@page import="it.swimv2.entities.remoteEntities.IRisposta"%>
<%@page import="it.swimv2.entities.remoteEntities.IAbilita"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	IDomanda domanda = (IDomanda) request.getAttribute("domanda");

	IRisposta[] risposte = (IRisposta[]) request
			.getAttribute("arrayRisposte");

	if (risposte == null || risposte.length == 0) {
		response.sendRedirect("index.jsp");
	}

	int idDomanda = domanda.getId();
	String nomeCreatore = domanda.getCreatore().getUsername();
	String titolo = domanda.getTitolo();
	String descrizioneDomanda = domanda.getDescrizione();
	//	domanda.getData();

	IAbilita[] abilita = domanda.getAbilita();

	String nomiAbilita;
	nomiAbilita = "";
	for (IAbilita a : abilita) {
		nomiAbilita += a.getNome() + " ";
	}
	String titoloDomanda = domanda.getTitolo();
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="index" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Domanda:
			<%=titolo%></div><br><br>
			<h3>Descrizione:</h3><br><p><%= descrizioneDomanda %></p><br><br>
			<h3>Abilita:</h3><br><p><%= nomiAbilita %></p><br><br>
			<h3>Creatore:</h3><br><p><%= nomeCreatore %></p><br><br>
		<div class="articles">
			<table>
				<tr>
					<td style="height: 49px">Risposta</td>
					<td style="height: 49px">Punteggio</td>
					<td style="height: 49px">Nome Utente</td>
					<td style="height: 49px"></td>
				</tr>
				<%
					for (IRisposta r : risposte) {
				%>
				<tr>
					<td style="height: 49px"><%=r.getDescrizione()%></td>
					<td style="height: 49px"><%=r.getFeedback()%></td>
					<td style="height: 49px"><%=r.getUtente().getUsername()%></td>
					<td style="height: 49px"><a
						href="RilasciaFeedback?id=<%=r.getId()%>">Assegna Feedback</a></td>

				</tr>
				<%
					}
				%>
			</table>
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

								<%=messaggioLogin%><br />
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

		<span id="Benvenuto"> <br />Benvenuto <br /> <%=utenteLoggato%>!
			<br /> <br />
		</span>
		<form action="Logout" method="post">
			<input id="immagineLogout" name="submit" type="image"
				src="css/images/button-logout.jpg" alt="Logout" />
		</form>
		<%
			}
		%>
	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
