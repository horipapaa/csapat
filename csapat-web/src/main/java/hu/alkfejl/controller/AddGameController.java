package hu.alkfejl.controller;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.Game;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddGameController")
public class AddGameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamDAO teamDAO = TeamDAOImpl.getInstance();
        List<Team> teams = teamDAO.findAll();
        req.setAttribute("teams", teams);
        req.getRequestDispatcher("add-game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Game game = new Game();
        GameDAO gameDAO = GameDAOImpl.getInstance();

        try {
            game.setDate(req.getParameter("match-date"));
            game.setReferee(req.getParameter("referee"));
            game.setLocation(req.getParameter("location"));
            game.setHomeTeamId(Integer.parseInt(req.getParameter("home-team")));
            game.setAwayTeamId(Integer.parseInt(req.getParameter("away-team")));
            game.setTravelInfo(req.getParameter("travel-info"));

            if(gameDAO.addGame(game)) {
                resp.sendRedirect("GameController");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
