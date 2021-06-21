<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:pagetemplate>
    <h1>Üzenet írása</h1>
    <form action="${pageContext.request.contextPath}/SendMessageController" method="post">
        <fieldset>
            <legend>${requestScope.team.teamName}</legend>
            <label for="from">Feladó</label><br>
            <select id="from" name="from">
                <c:forEach items="${requestScope.players}" var="player">
                    <option value="${player.id}">${player.name}</option>
                </c:forEach>
            </select><br>
            <label for="to">Címzett</label><br>
            <select id="to" name="to">
                <c:forEach items="${requestScope.players}" var="player">
                    <option value="${player.id}">${player.name}</option>
                </c:forEach>
            </select><br>
            <textarea rows="5" cols="60" name="content">Üzenet szövege...</textarea><br>
            <input type="hidden" value="${requestScope.team.id}" name="team-id">
            <input type="submit" value="Küldés">
        </fieldset>
    </form>
</t:pagetemplate>
