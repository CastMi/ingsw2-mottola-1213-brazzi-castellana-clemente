<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String utenteLoggato = (String) request.getSession().getAttribute(
			"nomeUtente");
%>

<jsp:include page="Header.jsp">
	<jsp:param name="titolo" value="Home page Utente" />
</jsp:include>

<div id="content">
	<div class="right">

		<div id="contentTitle">Home page Amministratore</div>
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

		<span id="Benvenuto"><h2>
				Benvenuto <br />
				<%
					out.print(utenteLoggato);
				%>!
			</h2>
			<br /> <br />
			<form action="Logout" method="post">
				<input id="immagineLogout" name="submit" type="image"
					src="css/images/button-logout.jpg" alt="Logout" />
			</form> </span>

	</div>
	<div style="clear: both;"></div>
</div>

<jsp:include page="Footer.jsp" />
