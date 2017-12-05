<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Nouveau Virement</title>
</head>
<body bgcolor="#fffacd">
<h2>Effectuer un virement :</h2>
<hr color="navyblue">
<form:form method="post" modelAttribute="transferModel" action="/transfers/submit">
    <div id="transfer">
        <form:label path="sendingIBAN" for="sendingIBAN">IBAN émetteur</form:label>
        <form:input path="sendingIBAN" id="sendingIBAN" size="30"/>
        <form:errors path="sendingIBAN"/><br/>
        <form:label path="amount" for="amount">Montant</form:label>
        <form:input path="amount" id="amount" size="15"/>
        <form:errors path="amount"/><br/>
        <form:label path="beneficiaryIban" for="beneficiaryIban">IBAN bénéficiaire</form:label>
        <form:input path="beneficiaryIban" id="beneficiaryIban" size="30"/>
        <form:errors path="beneficiaryIban"/><br/>
        <form:label path="valueDate" for="valueDate">Date de valeur</form:label>
        <form:input type="date" path="valueDate" id="valueDate" size="30"/>
        <form:errors path="valueDate"/><br/>
        <form:label path="wording" for="wording">Libellé</form:label>
        <form:input path="wording" id="wording" size="30"/>
        <form:errors path="wording"/><br/>
    </div>
    <form:button name="Submit" value="Envoyer" id="envoyer">Envoyer</form:button>
</form:form>
</body>
</html>