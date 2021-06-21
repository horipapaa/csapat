<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:pagetemplate>
    <h1>Mérkőzés hozzáadása</h1>
    <form action="${pageContext.request.contextPath}/AddGameController" method="post">
        <fieldset>
            <legend>Mérkőzés adatai</legend>
            <label for="match-date">Időpont</label><br>
            <input type="time" id="match-date" name="match-date"><br>
            <label for="referee">Játékvezető</label><br>
            <input type="text" id="referee" name="referee"><br>
            <label for="referee">Helyszín</label><br>
            <input type="text" id="location" name="location"><br>
            <label for="home-team">Hazai csapat</label><br>
            <select id="home-team" name="home-team">
                <c:forEach items="${requestScope.teams}" var="team">
                    <option value="${team.id}">${team.teamName}</option>
                </c:forEach>
            </select><br>
            <label for="away-team">Vendég csapat</label><br>
            <select id="away-team" name="away-team">
                <c:forEach items="${requestScope.teams}" var="team">
                    <option value="${team.id}">${team.teamName}</option>
                </c:forEach>
            </select><br>
            <label for="travel-info"></label>
            <textarea rows="5" cols="60" name="travel-info" id="travel-info">Utazási információ...</textarea><br>
            <input type="submit" value="Hozzáadás">
        </fieldset>
    </form>
</t:pagetemplate>