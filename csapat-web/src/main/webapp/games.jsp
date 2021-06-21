<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:pagetemplate>
    <span>
        <a href="${pageContext.request.contextPath}/MessageController">Üzenetek</a>
        <h1>Mérkőzések</h1>
    </span>
    <a href="${pageContext.request.contextPath}/AddGameController">+ Mérkőzés hozzáadása</a>
    <h2>Hátralevő mérkőzések</h2>
    <c:forEach items="${requestScope.futureGames}" var="game">
        <div class="card">
            <form action="${pageContext.request.contextPath}/GameController" method="post">
                <b><c:forEach var="team" items="${requestScope.teams}">
                    <c:if test="${team.id == game.homeTeamId}">${team.teamName}</c:if>
                </c:forEach>
                -
                <c:forEach var="team" items="${requestScope.teams}">
                    <c:if test="${team.id == game.awayTeamId}">${team.teamName}</c:if>
                </c:forEach>
                </b><br />
                <p>Bíró: ${game.referee}</p>
                <p>Helyszín: ${game.location}</p>
                <p>Utazási információ: ${game.travelInfo}</p>
                <p>Hazai játékosok:</p>
                <ul>
                    <c:forEach var="player" items="${requestScope.players}">
                        <c:if test="${player.currentTeamId == game.homeTeamId}">
                            <li>${player.name}</li>
                        </c:if>
                    </c:forEach>
                </ul>
                <p>Vendég játékosok:</p>
                <ul>
                    <c:forEach var="player" items="${requestScope.players}">
                        <c:if test="${player.currentTeamId == game.awayTeamId}">
                            <li>${player.name}</li>
                        </c:if>
                    </c:forEach>
                </ul>
                <label for="result">Eredmény:</label><br>
                <input type="text" id="result" name="result" required/><br>
                <input type="hidden" value="${game.id}" name="game-id">
                <input type="submit" value="Eredmény mentése">
            </form>
        </div>
    </c:forEach>
    <h2>Befejezett mérkőzések</h2>
    <c:forEach items="${requestScope.completedGames}" var="game">
        <div class="card">
            <b><c:forEach var="team" items="${requestScope.teams}">
                <c:if test="${team.id == game.homeTeamId}">${team.teamName}</c:if>
            </c:forEach>
                -
                <c:forEach var="team" items="${requestScope.teams}">
                    <c:if test="${team.id == game.awayTeamId}">${team.teamName}</c:if>
                </c:forEach>
            </b><br />
            <p><b>${game.result}</b></p>
            <p>Bíró: ${game.referee}</p>
            <p>Helyszín: ${game.location}</p>
            <p>Utazási információ: ${game.travelInfo}</p>
            <p>Hazai játékosok:</p>
            <ul>
                <c:forEach var="player" items="${requestScope.players}">
                    <c:if test="${player.currentTeamId == game.homeTeamId}">
                        <li>${player.name}</li>
                    </c:if>
                </c:forEach>
            </ul>
            <p>Vendég játékosok:</p>
            <ul>
                <c:forEach var="player" items="${requestScope.players}">
                    <c:if test="${player.currentTeamId == game.awayTeamId}">
                        <li>${player.name}</li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</t:pagetemplate>