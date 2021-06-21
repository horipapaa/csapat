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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamDAO teamDAO = TeamDAOImpl.getInstance();
        MessageDAO messageDAO = MessageDAOImpl.getInstance();
        PlayerDAO playerDAO = PlayerDAOImpl.getInstance();

        List<Team> teams = teamDAO.findAll();
        List<Message> messages = new ArrayList<>();
        List<Player> players = playerDAO.findAll();

        for (Team team: teams) {
            messages.addAll(messageDAO.getTeamMessages(team));
        }

        req.setAttribute("teams", teams);
        req.setAttribute("messages", messages);
        req.setAttribute("players", players);
        req.getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
