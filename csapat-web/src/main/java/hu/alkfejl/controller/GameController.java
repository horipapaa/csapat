package hu.alkfejl.controller;

import hu.alkfejl.dao.*;
import hu.alkfejl.model.Game;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GameController")
public class GameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameDAO gameDAO = GameDAOImpl.getInstance();
        PlayerDAO playerDAO = PlayerDAOImpl.getInstance();
        TeamDAO teamDAO = TeamDAOImpl.getInstance();

        List<Game> futureGames = gameDAO.getFutureGames();
        List<Game> completedGames = gameDAO.getCompletedGames();
        List<Player> players = playerDAO.findAll();
        List<Team> teams = teamDAO.findAll();

        req.setAttribute("futureGames", futureGames);
        req.setAttribute("completedGames", completedGames);
        req.setAttribute("players", players);
        req.setAttribute("teams", teams);
        req.getRequestDispatcher("games.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Game game = new Game();
        GameDAO gameDAO = GameDAOImpl.getInstance();

        try {
            game.setId(Integer.parseInt(req.getParameter("game-id")));
            game.setResult(req.getParameter("result"));

            if(gameDAO.completeGame(game)) {
                resp.sendRedirect("GameController");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
