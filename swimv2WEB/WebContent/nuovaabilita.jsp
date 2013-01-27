<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");

	if (utenteLoggato == null || utenteLoggato.isEmpty()) {
		response.sendRedirect("index.jsp");
	}
%>
<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="richiesta abilità" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Richiesta abilità</div>
		<div class="articles">
			<div class="main_content">
				<form action="InviaRichiestaAbilita" method="post">
					<fieldset>
						<table>
							<tr>
								<td class="td_campi_form"><label for="nomeAbilita">Nome
										abilità:</label></td>
								<td style="height: 49px"><input name="nomeAbilita"
									type="text" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"><label for="descrizione">Descrizione:</label></td>
								<td style="height: 49px"><input name="descrizione"
									type="text" style="width: 185px" /></td>
							</tr>
							<tr>
								<td class="td_campi_form"></td>
								<td align=center><input name="submit" type="submit"
									alt="InviaRichiestaAbilita" /></td>
							</tr>
						</table>
					</fieldset>
				</form>
				<%
					String messaggioRegistrazione = (String) request
							.getAttribute("messaggio");

					if (messaggioRegistrazione != null
							&& !messaggioRegistrazione.isEmpty()) {
				%>
				<div id="erroreRegistrazione">

					<%
						out.print(messaggioRegistrazione);
					%><br />
				</div>
				<%
					}
				%>
			</div>
		</div>

	</div>

	<div class="left">

		<h2>
			<span id="Benvenuto"> Benvenuto <br /> <%
 	out.print(utenteLoggato);
 %>! <br /> <br /></span>
		</h2>
		<form action="Logout" method="post">
			<input id="immagineLogout" name="submit" type="image"
				src="css/images/button-logout.jpg" alt="Logout" />
		</form>
		<br />
			<a href="homeUtente.jsp">Profilo</a>
		<br />
			<a href="TutteLeAbilita">Le tue abilità</a>
		<br />
			<a href="nuovaabilita.jsp">Aggiungi abilità</a>
	</div>
	<div style="clear: both;"></div>
</div>


<jsp:include page="Footer.jsp" />