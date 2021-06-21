<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:pagetemplate>
    <span>
        <a href="${pageContext.request.contextPath}/GameController">Mérkőzések</a>
        <h1>Üzenetek</h1>
    </span>
     <c:forEach var="team" items="${requestScope.teams}">
         <div class="card">
             <h2>${team.teamName}</h2>
             <c:forEach var="message" items="${requestScope.messages}">
                 <c:if test="${team.id == message.teamId}">
                     <b><c:forEach var="player" items="${requestScope.players}">
                         <c:if test="${player.id == message.from}">${player.name}</c:if>
                     </c:forEach>
                         >
                     <c:forEach var="player" items="${requestScope.players}">
                         <c:if test="${player.id == message.to}">${player.name}</c:if>
                     </c:forEach></b><br />
                     ${message.content}<br />
                 </c:if>
             </c:forEach>
             <a href="${pageContext.request.contextPath}/SendMessageController?teamId=${team.id}">+ Üzenet írása</a>
         </div>
     </c:forEach>
</t:pagetemplate>
