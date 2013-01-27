<%@page import="it.swimv2.entities.remoteEntities.IAbilita"%>
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
								<td class="td_campi_form"><label for="nomeUtente">Username:</label></td>
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
								<%
									IAbilita[] iAbilita = (IAbilita[]) request.getAttribute("abilita");
									if (iAbilita != null) {
								%>
								<td class="td_campi_form"><label for="abilita">Abilità:</label></td>
								<td style="height: 49px"><select name="abilita">
										<%
											for (IAbilita a : iAbilita) {
										%>
										<option value="<%=a.getNome()%>"><%=a.getNome()%></option>


										<%
											}
										%>
								</select></td>
								<%
									}
								%>

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
				<%
					String messaggioRegistrazione = (String) request
							.getAttribute("messaggioRegistrazione");

					if (messaggioRegistrazione != null
							&& !messaggioRegistrazione.isEmpty()) {
				%>
				<br />
				<div id="erroreRegistrazione">

					<%
						out.print(messaggioRegistrazione);
					%>
				</div>
				<%
					}
				%>
			</div>

		</div>
	</div>
	<jsp:include page="leftCode.jsp" />
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
