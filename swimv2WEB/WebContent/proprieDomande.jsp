<%@page import="it.swimv2.entities.remoteEntities.IDomanda"%>
<%@page import="it.swimv2.entities.remoteEntities.IAbilita"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Ricerca" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">
			Proprie Domande<br />
		</div>
		<div class="articles">
			<div class="main_content">

				<%
					IDomanda[] domande = (IDomanda[]) request
							.getAttribute("arrayProprieDomande");
					if (domande != null && domande.length > 0) {
				%>
				<h2>Mie Domande:</h2>
				<table>
					<tr>
						<td style="height: 49px">Titolo</td>
						<td style="height: 49px">Testo Domanda</td>
						<td style="height: 49px"></td>
					</tr>
					<%
						for (IDomanda d : domande) {
					%>
					<tr>
						<td style="height: 49px"><%=d.getTitolo()%></td>
						<td style="height: 49px"><%=d.getDescrizione()%></td>
						<td style="height: 49px"><a
							href="ApriConversazione?id=<%=d.getId()%>">Apri</a></td>
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

		<h2>Crea Domanda</h2>
		<form action="CreaDomanda" method="post">
			<fieldset>
				<table>
					<tr>
						<td class="td_campi_form"><label for="titoloDomanda">Titolo:</label></td>
						<td style="height: 49px"><input name="titoloDomanda"
							type="text" style="width: 185px" /></td>
					</tr>
					<tr>
						<td class="td_campi_form"><label for="descrizioneDomanda">Descrizione:</label></td>
						<td style="height: 49px"><textarea name="descrizioneDomanda"
								role="combobox" rows="3"></textarea></td>
					</tr>
					<tr>
						<%
							IAbilita[] abilita = (IAbilita[]) request.getAttribute("abilita");
							if (abilita != null) {
						%>
						<td class="td_campi_form"><label for="nomeAbilita">Abilità:</label></td>
						<td style="height: 49px"><select name="nomeAbilita">
								<%
									for (IAbilita a : abilita) {
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
						<td align=center><input name="submit" type="submit"
							value="crea" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

	<div class="left">
		<%
			String utenteLoggato = (String) request.getSession().getAttribute(
					"nomeUtente");
			if (utenteLoggato != null && !utenteLoggato.isEmpty()) {
		%>
		<span id="Benvenuto"><h2>
				Benvenuto <br />
				<%
					out.print(utenteLoggato);
				%>!

			</h2> <br /> <br /></span>
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