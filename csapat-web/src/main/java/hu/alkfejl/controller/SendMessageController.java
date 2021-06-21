package hu.alkfejl.controller;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SendMessageController")
public class SendMessageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamDAO teamDAO = TeamDAOImpl.getInstance();
        PlayerDAO playerDAO = PlayerDAOImpl.getInstance();
        String teamIdStr = req.getParameter("teamId");
        List<Team> teams = teamDAO.findAll();
        Team team = null;

        if(teamIdStr != null && !teamIdStr.isEmpty()){
            int teamId = Integer.parseInt(teamIdStr);
            for (Team t: teams) {
                if(t.getId() == teamId) {
                     team = t;
                }
            }
            List<Player> players = playerDAO.findPlayersOfTeam(team);

            req.setAttribute("team", team);
            req.setAttribute("players", players);
        }

        req.getRequestDispatcher("send-message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Message message = new Message();
        MessageDAO messageDAO = MessageDAOImpl.getInstance();

        try {
            message.setFrom(Integer.parseInt(req.getParameter("from")));
            message.setTo(Integer.parseInt(req.getParameter("to")));
            message.setTeamId(Integer.parseInt(req.getParameter("team-id")));
            message.setContent(req.getParameter("content"));

            if(messageDAO.saveMessage(message) != null) {
                resp.sendRedirect("MessageController");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
